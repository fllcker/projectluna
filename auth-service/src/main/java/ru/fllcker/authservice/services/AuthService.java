package ru.fllcker.authservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.fllcker.authservice.clients.UsersClient;
import ru.fllcker.authservice.domain.JwtAuthentication;
import ru.fllcker.authservice.dto.JwtResponse;
import ru.fllcker.authservice.dto.SignInDto;
import ru.fllcker.authservice.dto.SignUpDto;
import ru.fllcker.authservice.dto.User;
import ru.fllcker.authservice.models.Token;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {
    private final PasswordEncoder encoder;
    private final JwtProvider jwtProvider;
    private final TokensService tokensService;
    private final UsersClient usersClient;

    public JwtResponse login(SignInDto dto) {
        User user = usersClient.findByEmail(dto.getEmail());

        if (!encoder.matches(dto.getPassword(), user.getPassword()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong data");

        String accessToken = jwtProvider.generateToken(user, false);
        String refreshToken = jwtProvider.generateToken(user, true);
        tokensService.create(new Token(refreshToken, user.getId()));

        return new JwtResponse(accessToken, refreshToken);
    }

    public JwtResponse signup(SignUpDto dto) {
        User user = User.builder()
                .email(dto.getEmail())
                .password(encoder.encode(dto.getPassword()))
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .build();

        usersClient.create(user);

        String accessToken = jwtProvider.generateToken(user, false);
        String refreshToken = jwtProvider.generateToken(user, true);
        tokensService.create(new Token(refreshToken, user.getId()));

        return new JwtResponse(accessToken, refreshToken);
    }

    public JwtResponse getTokensByRefresh(String refreshToken, boolean refresh) {
        String subject = jwtProvider.getRefreshClaims(refreshToken)
                .getSubject();

        if (!jwtProvider.validateRefreshToken(refreshToken))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);

        Token token = tokensService.findByValue(refreshToken);

        if (!token.getValue().equals(refreshToken))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);

        User user = usersClient.findByEmail(subject);

        String accessToken = jwtProvider.generateToken(user, false);
        if (!refresh) {
            return new JwtResponse(accessToken, null);
        }

        String newRefreshToken = jwtProvider.generateToken(user, true);
        tokensService.create(new Token(newRefreshToken, user.getId()));
        return new JwtResponse(accessToken, newRefreshToken);
    }

    public JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }
}
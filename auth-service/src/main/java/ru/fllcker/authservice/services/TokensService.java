package ru.fllcker.authservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.fllcker.authservice.models.Token;
import ru.fllcker.authservice.repositories.ITokensRepository;

@Service
@RequiredArgsConstructor
public class TokensService {
    private final ITokensRepository tokensRepository;

    public Token create(Token token) {
        return tokensRepository.save(token);
    }

    public Token findById(String id) {
        return tokensRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Token not found!"));
    }

    public Token findByValue(String value) {
        return tokensRepository.findByValue(value)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Token not found!"));
    }

    public boolean existsByValue(String value) {
        return tokensRepository.existsByValue(value);
    }
}

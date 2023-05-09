package ru.fllcker.authservice.utils;

import io.jsonwebtoken.Claims;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.fllcker.authservice.domain.JwtAuthentication;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JwtUtils {
    public static JwtAuthentication generate(Claims claims) {
        final JwtAuthentication jwtInfoToken = new JwtAuthentication();
        jwtInfoToken.setEmail(claims.getSubject());
        return jwtInfoToken;
    }
}

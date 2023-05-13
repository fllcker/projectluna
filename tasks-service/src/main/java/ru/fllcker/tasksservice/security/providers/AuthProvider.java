package ru.fllcker.tasksservice.security.providers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.fllcker.tasksservice.security.domain.JwtAuthentication;

@Service
public class AuthProvider {
    private JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }

    public String getSubject() {
        return getAuthInfo().getEmail();
    }
}

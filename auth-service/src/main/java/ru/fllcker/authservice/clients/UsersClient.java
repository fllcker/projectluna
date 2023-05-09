package ru.fllcker.authservice.clients;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.fllcker.authservice.dto.User;

@Service
@RequiredArgsConstructor
public class UsersClient {
    private final RestTemplate restTemplate;
    private final String USERS_SERVICE = "lb://USERS-SERVICE/users/";

    public User findByEmail(String email) {
        return restTemplate.getForObject(USERS_SERVICE + "private/email?email=" + email, User.class);
    }

    public void create(User user) {
        restTemplate.postForObject(USERS_SERVICE + "private", user, User.class);
    }
}

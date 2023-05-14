package ru.fllcker.workspacesservice.clients;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.fllcker.workspacesservice.dto.User;
import ru.fllcker.workspacesservice.utils.HttpUtils;

@Service
@RequiredArgsConstructor
public class UsersClient {
    private final RestTemplate restTemplate;
    private final String USERS_SERVICE = "lb://USERS-SERVICE/users/";

    public User findByEmail(String email) {
        return restTemplate.exchange(
                USERS_SERVICE + "private/email?email=" + email,
                HttpMethod.GET,
                HttpUtils.generateWithPrivateHeader(),
                User.class).getBody();
    }
}

package ru.fllcker.tasksservice.clients;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.fllcker.tasksservice.utils.HttpUtils;


@Service
@RequiredArgsConstructor
public class WorkspacesClient {
    private final RestTemplate restTemplate;
    private final String WORKSPACES_SERVICE = "lb://WORKSPACES-SERVICE/workspaces/";

    public Boolean isContainsInWorkspaces(String workspaceId, String email) {
        return restTemplate.exchange(
                WORKSPACES_SERVICE + "private/contains?workspaceId=" + workspaceId + "&email=" + email,
                HttpMethod.GET,
                HttpUtils.generateWithPrivateHeader(),
                Boolean.class
        ).getBody();
    }
}

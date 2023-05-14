package ru.fllcker.tasksservice.clients;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@RequiredArgsConstructor
public class WorkspacesClient {
    private final RestTemplate restTemplate;
    private final String WORKSPACES_SERVICE = "lb://WORKSPACES-SERVICE/workspaces/";

    public Boolean isContainsInWorkspaces(String workspaceId, String email) {
        return restTemplate.getForObject(WORKSPACES_SERVICE + "private/contains?workspaceId=" + workspaceId + "&email=" + email, Boolean.class);
    }
}

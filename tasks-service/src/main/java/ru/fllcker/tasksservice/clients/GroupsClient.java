package ru.fllcker.tasksservice.clients;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.fllcker.tasksservice.dto.Group;

@Service
@RequiredArgsConstructor
public class GroupsClient {
    private final RestTemplate restTemplate;

    private final String GROUPS_SERVICE = "lb://GROUPS-SERVICE/groups/";

    public Group findByIdIfUserIsMember(String id, String accessEmail) {
        return restTemplate.getForObject(GROUPS_SERVICE + "private/id?id=" + id + "&accessEmail=" + accessEmail, Group.class);
    }
}

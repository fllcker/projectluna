package ru.fllcker.tasksservice.clients;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.fllcker.tasksservice.dto.Group;
import ru.fllcker.tasksservice.utils.HttpUtils;

@Service
@RequiredArgsConstructor
public class GroupsClient {
    private final RestTemplate restTemplate;

    private final String GROUPS_SERVICE = "lb://GROUPS-SERVICE/groups/";

    public Group findByIdIfUserIsMember(String id, String accessEmail) {
        return restTemplate.exchange(
                GROUPS_SERVICE + "private/id?id=" + id + "&accessEmail=" + accessEmail,
                HttpMethod.GET,
                HttpUtils.generateWithPrivateHeader(),
                Group.class
        ).getBody();
    }
}

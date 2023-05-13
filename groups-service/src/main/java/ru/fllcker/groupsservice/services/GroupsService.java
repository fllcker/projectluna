package ru.fllcker.groupsservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.fllcker.groupsservice.clients.UsersClient;
import ru.fllcker.groupsservice.clients.WorkspacesClient;
import ru.fllcker.groupsservice.dto.CreateGroupDto;
import ru.fllcker.groupsservice.dto.User;
import ru.fllcker.groupsservice.models.Group;
import ru.fllcker.groupsservice.repositories.IGroupUserRepository;
import ru.fllcker.groupsservice.repositories.IGroupsRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupsService {
    private final IGroupsRepository groupsRepository;
    private final IGroupUserRepository groupUserRepository;
    private final UsersClient usersClient;
    private final WorkspacesClient workspacesClient;

    public Group create(CreateGroupDto createGroupDto, String userEmail) {
        if (!workspacesClient.isContainsInWorkspaces(createGroupDto.getWorkspaceId(), userEmail))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No access!");

        User user = usersClient.findByEmail(userEmail);

        Group group = Group.builder()
                .title(createGroupDto.getTitle())
                .workspaceId(createGroupDto.getWorkspaceId())
                .creatorId(user.getId())
                .build();

        return groupsRepository.save(group);
    }

    public void addDefaultGroups(String workspaceId) {
        Group toDoGroup = Group.builder()
                .title("To Do")
                .workspaceId(workspaceId)
                .build();

        Group inProgressGroup = Group.builder()
                .title("In progress")
                .workspaceId(workspaceId)
                .build();

        Group doneGroup = Group.builder()
                .title("Done")
                .workspaceId(workspaceId)
                .build();

        List<Group> defaultGroups = List.of(toDoGroup, inProgressGroup, doneGroup);
        groupsRepository.saveAll(defaultGroups);
    }
}

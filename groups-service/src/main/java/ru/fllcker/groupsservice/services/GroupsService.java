package ru.fllcker.groupsservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.fllcker.groupsservice.clients.UsersClient;
import ru.fllcker.groupsservice.clients.WorkspacesClient;
import ru.fllcker.groupsservice.dto.AddingPersonalGroupDto;
import ru.fllcker.groupsservice.dto.CreateGroupDto;
import ru.fllcker.groupsservice.dto.GroupAndMembersDto;
import ru.fllcker.groupsservice.dto.User;
import ru.fllcker.groupsservice.models.Group;
import ru.fllcker.groupsservice.models.GroupUser;
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

    public Group findById(String id) {
        return groupsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Group not found!"));
    }

    public GroupAndMembersDto findGroupAndMemberById(String id) {
        Group group = this.findById(id);
        List<GroupUser> groupUsers = groupUserRepository.findByGroupId(group.getId());

        return new GroupAndMembersDto(group, groupUsers);
    }

    public List<GroupAndMembersDto> findGroupsByWorkspace(String workspaceId, String userEmail) {
        if (!workspacesClient.isContainsInWorkspaces(workspaceId, userEmail))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No access!");

        User user = usersClient.findByEmail(userEmail);

        List<Group> groups = groupsRepository.findByWorkspaceId(workspaceId);
        List<String> groupsIds = groups.stream().map(Group::getId).toList();
        List<GroupUser> relations = groupUserRepository.findByGroupIdIn(groupsIds);

        return groups.stream().map(group -> {
            List<GroupUser> rel = relations.stream().filter(v -> v.getGroupId().equals(group.getId())).toList();
            return new GroupAndMembersDto(group, rel);
        }).toList();
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

    public void addGroupForUser(AddingPersonalGroupDto addingPersonalGroupDto) {
        Group group = Group.builder()
                .title("In progress (Personal)")
                .workspaceId(addingPersonalGroupDto.getWorkspaceId())
                .creatorId(addingPersonalGroupDto.getUserId()).build();

        groupsRepository.save(group);

        GroupUser groupUser = GroupUser.builder()
                .groupId(group.getId())
                .userId(addingPersonalGroupDto.getUserId()).build();

        groupUserRepository.save(groupUser);
    }

    public Group findByIdIfUserIsMember(String id, String accessEmail) {
        User user = usersClient.findByEmail(accessEmail);

        GroupUser groupUser = groupUserRepository.findByGroupIdAndUserId(id, user.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Group not found!"));

        return this.findById(groupUser.getGroupId());
    }
}

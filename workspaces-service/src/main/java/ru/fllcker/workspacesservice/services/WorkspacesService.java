package ru.fllcker.workspacesservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.fllcker.workspacesservice.clients.UsersClient;
import ru.fllcker.workspacesservice.dto.AddingPersonalGroupDto;
import ru.fllcker.workspacesservice.dto.CreateWorkspaceDto;
import ru.fllcker.workspacesservice.dto.User;
import ru.fllcker.workspacesservice.models.Workspace;
import ru.fllcker.workspacesservice.models.WorkspaceUser;
import ru.fllcker.workspacesservice.mq.AddingPersonalGroupProducer;
import ru.fllcker.workspacesservice.mq.AddingDefaultGroupsProducer;
import ru.fllcker.workspacesservice.repositories.IWorkspaceUserRepository;
import ru.fllcker.workspacesservice.repositories.IWorkspacesRepository;
import ru.fllcker.workspacesservice.security.providers.AuthProvider;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkspacesService {
    private final IWorkspacesRepository workspacesRepository;
    private final IWorkspaceUserRepository workspaceUserRepository;
    private final AuthProvider authProvider;
    private final UsersClient usersClient;
    private final AddingDefaultGroupsProducer workspacesProducer;
    private final AddingPersonalGroupProducer addingPersonalGroupProducer;

    public Workspace create(CreateWorkspaceDto createWorkspaceDto) {
        User creator = usersClient.findByEmail(authProvider.getSubject());

        Workspace workspace = Workspace.builder()
                .title(createWorkspaceDto.getTitle())
                .description(createWorkspaceDto.getDescription())
                .type(createWorkspaceDto.getType())
                .creatorId(creator.getId())
                .build();

        workspacesRepository.save(workspace);
        this.addMember(workspace.getId(), creator.getId());

        workspacesProducer.executeAddDefaultGroups(workspace.getId());
        return workspace;
    }

    public void addMember(String workspaceId, String userId) {
        WorkspaceUser workspaceUser = WorkspaceUser
                .builder()
                .userId(userId)
                .workspaceId(workspaceId).build();

        workspaceUserRepository.save(workspaceUser);

        // adding group for member
        AddingPersonalGroupDto addingPersonalGroupDto = new AddingPersonalGroupDto(workspaceId, userId);
        addingPersonalGroupProducer.executeAddPersonalGroup(addingPersonalGroupDto);
    }

    public Workspace findById(String id) {
        return workspacesRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Workspace not found!"));
    }

    public List<Workspace> findUserWorkspaces(String email) {
        User creator = usersClient.findByEmail(email);

        List<WorkspaceUser> workspaceUsers = workspaceUserRepository.findByUserId(creator.getId());
        List<String> workspaceIds = workspaceUsers.stream()
                .map(WorkspaceUser::getWorkspaceId)
                .toList();

        return workspacesRepository.findByIdIn(workspaceIds);
    }

    public List<Workspace> findUserWorkspaces() {
        return findUserWorkspaces(authProvider.getSubject());
    }

    public List<WorkspaceUser> findWorkspaceMembers(String id) {
        List<WorkspaceUser> members = workspaceUserRepository.findByWorkspaceId(id);

        // Verify access
        User user = usersClient.findByEmail(authProvider.getSubject());
        long count = members.stream().filter(v -> v.getUserId().equals(user.getId())).count();
        if (count == 0) throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No access!");

        return members;
    }
}

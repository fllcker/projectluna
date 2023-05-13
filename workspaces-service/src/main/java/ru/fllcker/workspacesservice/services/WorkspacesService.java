package ru.fllcker.workspacesservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.fllcker.workspacesservice.clients.UsersClient;
import ru.fllcker.workspacesservice.dto.CreateWorkspaceDto;
import ru.fllcker.workspacesservice.dto.User;
import ru.fllcker.workspacesservice.models.Workspace;
import ru.fllcker.workspacesservice.models.WorkspaceUser;
import ru.fllcker.workspacesservice.mq.WorkspacesProducer;
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
    private final WorkspacesProducer workspacesProducer;

    public Workspace create(CreateWorkspaceDto createWorkspaceDto) {
        User creator = usersClient.findByEmail(authProvider.getSubject());

        Workspace workspace = Workspace.builder()
                .title(createWorkspaceDto.getTitle())
                .description(createWorkspaceDto.getDescription())
                .type(createWorkspaceDto.getType())
                .creatorId(creator.getId())
                .build();

        workspace = workspacesRepository.save(workspace);
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
}

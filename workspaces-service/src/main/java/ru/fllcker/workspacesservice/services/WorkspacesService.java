package ru.fllcker.workspacesservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.fllcker.workspacesservice.clients.UsersClient;
import ru.fllcker.workspacesservice.dto.CreateWorkspaceDto;
import ru.fllcker.workspacesservice.dto.User;
import ru.fllcker.workspacesservice.models.Workspace;
import ru.fllcker.workspacesservice.repositories.IWorkspacesRepository;
import ru.fllcker.workspacesservice.security.providers.AuthProvider;

@Service
@RequiredArgsConstructor
public class WorkspacesService {
    private final IWorkspacesRepository workspacesRepository;
    private final AuthProvider authProvider;
    private final UsersClient usersClient;

    public Workspace create(CreateWorkspaceDto createWorkspaceDto) {
        User creator = usersClient.findByEmail(authProvider.getSubject());

        Workspace workspace = Workspace.builder()
                .title(createWorkspaceDto.getTitle())
                .description(createWorkspaceDto.getDescription())
                .type(createWorkspaceDto.getType())
                .creatorId(creator.getId())
                .build();

        return workspacesRepository.save(workspace);
    }

    public Workspace findById(String id) {
        return workspacesRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Workspace not found!"));
    }
}

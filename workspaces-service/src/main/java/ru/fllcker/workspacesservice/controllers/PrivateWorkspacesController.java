package ru.fllcker.workspacesservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.fllcker.workspacesservice.models.Workspace;
import ru.fllcker.workspacesservice.services.WorkspacesService;

@RestController
@RequestMapping("workspaces/private")
@RequiredArgsConstructor
public class PrivateWorkspacesController {
    private final WorkspacesService workspacesService;

    @GetMapping("id/{id}")
    public Workspace findById(@PathVariable String id) {
        return workspacesService.findById(id);
    }

    @GetMapping("contains")
    public Boolean isContainsUserInWorkspace(
            @RequestParam String workspaceId,
            @RequestParam String email
    ) {
        long count = workspacesService.findUserWorkspaces(email)
                .stream().filter(v -> v.getId().equals(workspaceId)).count();

        return count != 0;
    }
}

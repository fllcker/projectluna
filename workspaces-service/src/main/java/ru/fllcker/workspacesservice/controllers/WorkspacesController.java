package ru.fllcker.workspacesservice.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.fllcker.workspacesservice.dto.CreateWorkspaceDto;
import ru.fllcker.workspacesservice.models.Workspace;
import ru.fllcker.workspacesservice.services.WorkspacesService;

import java.util.List;

@RestController
@RequestMapping("workspaces")
@RequiredArgsConstructor
public class WorkspacesController {
    private final WorkspacesService workspacesService;

    @PostMapping
    public ResponseEntity<Workspace> create(@RequestBody @Valid CreateWorkspaceDto createWorkspaceDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());

        Workspace workspace = workspacesService.create(createWorkspaceDto);
        return ResponseEntity.ok(workspace);
    }

    @GetMapping("by/member")
    public ResponseEntity<List<Workspace>> findUserWorkspaces() {
        List<Workspace> userWorkspaces = workspacesService.findUserWorkspaces();
        return ResponseEntity.ok(userWorkspaces);
    }
}

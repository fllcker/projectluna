package ru.fllcker.groupsservice.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.fllcker.groupsservice.dto.CreateGroupDto;
import ru.fllcker.groupsservice.models.Group;
import ru.fllcker.groupsservice.security.providers.AuthProvider;
import ru.fllcker.groupsservice.services.GroupsService;

@RestController
@RequiredArgsConstructor
@RequestMapping("groups")
public class GroupsController {
    private final AuthProvider authProvider;
    private final GroupsService groupsService;

    @PostMapping
    public ResponseEntity<Group> create(@RequestBody @Valid CreateGroupDto createGroupDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());

        Group group = groupsService.create(createGroupDto, authProvider.getSubject());

        return ResponseEntity.ok(group);
    }
}

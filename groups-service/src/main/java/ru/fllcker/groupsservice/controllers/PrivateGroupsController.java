package ru.fllcker.groupsservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.fllcker.groupsservice.models.Group;
import ru.fllcker.groupsservice.services.GroupsService;

@RestController
@RequiredArgsConstructor
@RequestMapping("groups/private")
public class PrivateGroupsController {
    private final GroupsService groupsService;

    @GetMapping("id")
    public Group findByIdIfUserIsMember(@RequestParam String id,
                          @RequestParam String accessEmail) {
        return groupsService.findByIdIfUserIsMember(id, accessEmail);
    }
}

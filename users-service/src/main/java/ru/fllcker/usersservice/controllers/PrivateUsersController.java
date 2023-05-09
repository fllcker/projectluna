package ru.fllcker.usersservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fllcker.usersservice.dto.CreateUserDto;
import ru.fllcker.usersservice.models.User;
import ru.fllcker.usersservice.services.UsersService;

@RestController
@RequestMapping("users/private")
@RequiredArgsConstructor
public class PrivateUsersController {
    private final UsersService usersService;

    @PostMapping
    public User create(@RequestBody CreateUserDto createUserDto) {
        return usersService.create(createUserDto);
    }
}

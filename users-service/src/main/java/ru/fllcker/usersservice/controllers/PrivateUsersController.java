package ru.fllcker.usersservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("email")
    public User findByEmail(@RequestParam String email) {
        return usersService.findByEmail(email);
    }
}

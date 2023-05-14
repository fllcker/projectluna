package ru.fllcker.tasksservice.controllers;

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
import ru.fllcker.tasksservice.dto.CreateTaskDto;
import ru.fllcker.tasksservice.models.Task;
import ru.fllcker.tasksservice.services.TasksService;

@RestController
@RequiredArgsConstructor
@RequestMapping("tasks")
public class TasksController {
    private final TasksService tasksService;

    @PostMapping
    private ResponseEntity<Task> create(@RequestBody @Valid CreateTaskDto createTaskDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());

        Task task = tasksService.create(createTaskDto);
        return ResponseEntity.ok(task);
    }
}

package ru.fllcker.tasksservice.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.fllcker.tasksservice.dto.CreateTaskDto;
import ru.fllcker.tasksservice.models.Task;
import ru.fllcker.tasksservice.services.TasksService;

import java.util.List;

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

    @GetMapping("group/{groupId}")
    public ResponseEntity<List<Task>> findByGroupId(@PathVariable String groupId) {
        List<Task> tasks = tasksService.findByGroupId(groupId);
        return ResponseEntity.ok(tasks);
    }

    @PutMapping("id/{id}/group/{groupId}")
    public ResponseEntity<String> updateTaskGroup(@PathVariable String id, @PathVariable String groupId) {
        tasksService.updateTaskGroup(id, groupId);
        return ResponseEntity.ok().build();
    }
}

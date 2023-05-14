package ru.fllcker.tasksservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.fllcker.tasksservice.clients.GroupsClient;
import ru.fllcker.tasksservice.dto.CreateTaskDto;
import ru.fllcker.tasksservice.dto.Group;
import ru.fllcker.tasksservice.models.Task;
import ru.fllcker.tasksservice.repositories.ITasksRepository;
import ru.fllcker.tasksservice.security.providers.AuthProvider;
import ru.fllcker.tasksservice.utils.DateUtils;

@Service
@RequiredArgsConstructor
public class TasksService {
    private final ITasksRepository tasksRepository;
    private final AuthProvider authProvider;
    private final GroupsClient groupsClient;

    public Task create(CreateTaskDto createTaskDto) {
        Group group = groupsClient.findByIdIfUserIsMember(createTaskDto.getGroupId(), authProvider.getSubject());

        Task task = Task.builder()
                .title(createTaskDto.getTitle())
                .description(createTaskDto.getDescription())
                .deadLine(DateUtils.convertStringToDate(createTaskDto.getDeadLine()))
                .groupId(group.getId())
                .workspaceId(group.getWorkspaceId()).build();

        return tasksRepository.save(task);
    }
}

package ru.fllcker.tasksservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.fllcker.tasksservice.clients.GroupsClient;
import ru.fllcker.tasksservice.clients.UsersClient;
import ru.fllcker.tasksservice.dto.CreateTaskDto;
import ru.fllcker.tasksservice.dto.Group;
import ru.fllcker.tasksservice.dto.User;
import ru.fllcker.tasksservice.models.Task;
import ru.fllcker.tasksservice.repositories.ITasksRepository;
import ru.fllcker.tasksservice.security.providers.AuthProvider;
import ru.fllcker.tasksservice.utils.DateUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TasksService {
    private final ITasksRepository tasksRepository;
    private final AuthProvider authProvider;
    private final GroupsClient groupsClient;
    private final UsersClient usersClient;
    //private final WorkspacesClient workspacesClient;

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

    public Task findById(String id) {
        return tasksRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found!"));
    }

    public Boolean checkAccessToTask(String id, String email) {
        Task task = this.findById(id);
        User user = usersClient.findByEmail(email);

        if (task.getCreatorId().equals(user.getId())) return true;

        if (groupsClient.findByIdIfUserIsMember(task.getGroupId(), email) != null)
            return true;

        return false;
    }

    public List<Task> findByGroupId(String groupId) {
        return tasksRepository.findByGroupId(groupId);
    }

    public void updateTaskGroup(String id, String groupId) {
        if (checkAccessToTask(id, authProvider.getSubject()))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No access!");

        Group group = groupsClient.findByIdIfUserIsMember(groupId, authProvider.getSubject());

        Task task = this.findById(id);
        task.setGroupId(group.getId());
        tasksRepository.save(task);
    }

    public void deleteById(String id) {
        if (checkAccessToTask(id, authProvider.getSubject()))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No access!");

        tasksRepository.deleteById(id);
    }
}

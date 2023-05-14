package ru.fllcker.tasksservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.fllcker.tasksservice.models.Task;

import java.util.List;

@Repository
public interface ITasksRepository extends MongoRepository<Task, String> {
    List<Task> findByGroupId(String groupId);
    List<Task> findByWorkspaceId(String workspaceId);
}

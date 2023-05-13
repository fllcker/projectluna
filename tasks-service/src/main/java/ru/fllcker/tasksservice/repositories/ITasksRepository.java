package ru.fllcker.tasksservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.fllcker.tasksservice.models.Task;

@Repository
public interface ITasksRepository extends MongoRepository<Task, String> {
}

package ru.fllcker.workspacesservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.fllcker.workspacesservice.models.Workspace;

import java.util.List;

@Repository
public interface IWorkspacesRepository extends MongoRepository<Workspace, String> {
    List<Workspace> findByIdIn(List<String> id);
}

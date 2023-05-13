package ru.fllcker.workspacesservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.fllcker.workspacesservice.models.Workspace;

@Repository
public interface IWorkspacesRepository extends MongoRepository<Workspace, String> {
}

package ru.fllcker.workspacesservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.fllcker.workspacesservice.models.WorkspaceUser;

import java.util.List;

@Repository
public interface IWorkspaceUserRepository extends MongoRepository<WorkspaceUser, String> {
    List<WorkspaceUser> findByUserId(String userId);
}

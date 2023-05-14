package ru.fllcker.groupsservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.fllcker.groupsservice.models.Group;

import java.util.List;

@Repository
public interface IGroupsRepository extends MongoRepository<Group, String> {
    List<Group> findByWorkspaceId(String workspaceId);
}

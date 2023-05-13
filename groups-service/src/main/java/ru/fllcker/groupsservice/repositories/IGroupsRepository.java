package ru.fllcker.groupsservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.fllcker.groupsservice.models.Group;

@Repository
public interface IGroupsRepository extends MongoRepository<Group, String> {
}

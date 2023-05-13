package ru.fllcker.groupsservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.fllcker.groupsservice.models.GroupUser;

@Repository
public interface IGroupUserRepository extends MongoRepository<GroupUser, String> {
}

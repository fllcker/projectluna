package ru.fllcker.groupsservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.fllcker.groupsservice.models.GroupUser;

import java.util.List;

@Repository
public interface IGroupUserRepository extends MongoRepository<GroupUser, String> {
    List<GroupUser> findByGroupIdIn(List<String> groupIds);
    List<GroupUser> findByGroupId(String groupId);
}

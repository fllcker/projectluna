package ru.fllcker.usersservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.fllcker.usersservice.models.User;

@Repository
public interface IUsersRepository extends MongoRepository<User, String> {
    boolean existsByEmail(String email);
}

package ru.fllcker.usersservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.fllcker.usersservice.models.User;

import java.util.Optional;

@Repository
public interface IUsersRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}

package ru.fllcker.authservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.fllcker.authservice.models.Token;

import java.util.Optional;

@Repository
public interface ITokensRepository extends MongoRepository<Token, String> {
    Optional<Token> findByValue(String value);
    boolean existsByValue(String value);
}

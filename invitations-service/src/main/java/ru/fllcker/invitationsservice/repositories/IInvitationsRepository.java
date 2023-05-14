package ru.fllcker.invitationsservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.fllcker.invitationsservice.models.Invitation;

import java.util.List;

@Repository
public interface IInvitationsRepository extends MongoRepository<Invitation, String> {
    List<Invitation> findByTargetId(String targetId);
}

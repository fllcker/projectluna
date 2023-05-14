package ru.fllcker.invitationsservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.fllcker.invitationsservice.clients.UsersClient;
import ru.fllcker.invitationsservice.clients.WorkspacesClient;
import ru.fllcker.invitationsservice.dto.AddMemberToWorkspaceDto;
import ru.fllcker.invitationsservice.dto.CreateInvitationDto;
import ru.fllcker.invitationsservice.dto.User;
import ru.fllcker.invitationsservice.models.Invitation;
import ru.fllcker.invitationsservice.mq.AddingMemberProducer;
import ru.fllcker.invitationsservice.repositories.IInvitationsRepository;
import ru.fllcker.invitationsservice.security.providers.AuthProvider;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvitationsService {
    private final IInvitationsRepository invitationsRepository;
    private final AuthProvider authProvider;
    private final UsersClient usersClient;
    private final WorkspacesClient workspacesClient;
    private final AddingMemberProducer addingMemberProducer;

    public Invitation create(CreateInvitationDto createInvitationDto) {
        User creator = usersClient.findByEmail(authProvider.getSubject());
        User target = usersClient.findByEmail(createInvitationDto.getTargetEmail());

        if (!workspacesClient.isContainsInWorkspaces(createInvitationDto.getWorkspaceId(), creator.getEmail()))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No access!");

        Invitation invitation = Invitation.builder()
                .creatorId(creator.getId())
                .targetId(target.getId())
                .workspaceId(createInvitationDto.getWorkspaceId()).build();

        return invitationsRepository.save(invitation);
    }

    public Invitation findById(String id) {
        return invitationsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invitation not found!"));
    }

    public List<Invitation> findByTarget(String targetId) {
        return invitationsRepository.findByTargetId(targetId);
    }

    public List<Invitation> findInvitationsForUser(String email) {
        User user = usersClient.findByEmail(authProvider.getSubject());
        return this.findByTarget(user.getId());
    }

    public void accept(String invitationId) {
        User user = usersClient.findByEmail(authProvider.getSubject());
        Invitation invitation = this.findById(invitationId);

        if (!invitation.getTargetId().equals(user.getId()) || invitation.getAccepted())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invitation is not valid!");

        // adding user to workspace
        AddMemberToWorkspaceDto addMemberToWorkspaceDto = new AddMemberToWorkspaceDto(invitation.getWorkspaceId(), invitation.getTargetId());
        addingMemberProducer.executeAddMemberToWorkspace(addMemberToWorkspaceDto);

        // change accepted status
        invitation.setAccepted(true);
        invitationsRepository.save(invitation);
    }
}

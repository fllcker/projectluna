package ru.fllcker.invitationsservice.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.fllcker.invitationsservice.dto.CreateInvitationDto;
import ru.fllcker.invitationsservice.models.Invitation;
import ru.fllcker.invitationsservice.security.providers.AuthProvider;
import ru.fllcker.invitationsservice.services.InvitationsService;

import java.util.List;

@RestController
@RequestMapping("invitations")
@RequiredArgsConstructor
public class InvitationsController {
    private final InvitationsService invitationsService;
    private final AuthProvider authProvider;

    @PostMapping
    public ResponseEntity<Invitation> create(@RequestBody @Valid CreateInvitationDto createInvitationDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());

        Invitation invitation = invitationsService.create(createInvitationDto);
        return ResponseEntity.ok(invitation);
    }

    @GetMapping("accept/{id}")
    public ResponseEntity<String> acceptInvite(@PathVariable String id) {
        invitationsService.accept(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("me")
    public ResponseEntity<List<Invitation>> findInvitationsForMe() {
        List<Invitation> invitations = invitationsService.findInvitationsForUser(authProvider.getSubject());

        return ResponseEntity.ok(invitations);
    }
}

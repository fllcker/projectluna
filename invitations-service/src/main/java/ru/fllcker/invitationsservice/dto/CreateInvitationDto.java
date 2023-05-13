package ru.fllcker.invitationsservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateInvitationDto {
    @NotNull
    private String targetEmail;

    @NotNull
    private String workspaceId;
}

package ru.fllcker.invitationsservice.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "invitations")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Invitation {
    @Id
    private String id;

    @Builder.Default
    private LocalDate createdTime = LocalDate.now();

    private String creatorId;

    private String targetId;

    private String workspaceId;

    @Builder.Default
    private Boolean accepted = false;
}

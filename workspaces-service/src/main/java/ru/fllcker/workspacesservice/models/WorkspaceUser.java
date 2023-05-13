package ru.fllcker.workspacesservice.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "workspace_user")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class WorkspaceUser {
    @Id
    private String id;

    @Builder.Default
    private LocalDate createdTime = LocalDate.now();

    private String workspaceId;

    private String userId;
}

package ru.fllcker.workspacesservice.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "workspaces")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Workspace {
    @Id
    private String id;

    @Builder.Default
    private LocalDate createdTime = LocalDate.now();

    private String title;

    private String description;

    private String type;

    private String creatorId;
}

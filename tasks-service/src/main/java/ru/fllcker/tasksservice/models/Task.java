package ru.fllcker.tasksservice.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;

@Document(collection = "tasks")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Task {
    @Id
    private String id;

    @Builder.Default
    private LocalTime createdTime = LocalTime.now();

    private String title;

    private String description;

    private LocalDate deadLine;

    private String groupId;

    private String creatorId;

    private String workspaceId;
}

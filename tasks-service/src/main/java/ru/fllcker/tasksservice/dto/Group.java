package ru.fllcker.tasksservice.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Group {
    private String id;

    private LocalDate createdTime = LocalDate.now();

    private String title;

    private String creatorId;

    private String workspaceId;
}

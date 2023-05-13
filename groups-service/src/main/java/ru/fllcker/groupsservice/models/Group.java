package ru.fllcker.groupsservice.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "groups")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Group {
    @Id
    private String id;

    @Builder.Default
    private LocalDate createdTime = LocalDate.now();

    private String title;

    private String creatorId;
}

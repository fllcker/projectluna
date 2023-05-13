package ru.fllcker.groupsservice.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "group_user")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class GroupUser {
    @Id
    private String id;

    @Builder.Default
    private LocalDate createdTime = LocalDate.now();

    private String groupId;

    private String userId;
}

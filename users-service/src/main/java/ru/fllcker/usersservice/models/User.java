package ru.fllcker.usersservice.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;

@Document(collection = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class User {
    @Id
    private String id;

    @Builder.Default
    private LocalTime createdTime = LocalTime.now();

    private String email;

    private String password;

    private String firstName;

    private String lastName;
}



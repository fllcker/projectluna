package ru.fllcker.authservice.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;

@Document(collection = "tokens")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Token {
    @Id
    private String id;

    @Builder.Default
    private LocalTime createdTime = LocalTime.now();

    private String value;

    private String userId;
}

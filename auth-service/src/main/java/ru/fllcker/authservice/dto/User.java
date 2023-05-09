package ru.fllcker.authservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private String id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}

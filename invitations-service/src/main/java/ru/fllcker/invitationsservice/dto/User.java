package ru.fllcker.invitationsservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String id;

    private String email;

    private String password;

    private String firstName;

    private String lastName;
}




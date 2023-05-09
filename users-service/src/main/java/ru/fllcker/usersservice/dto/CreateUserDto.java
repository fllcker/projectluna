package ru.fllcker.usersservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateUserDto {
    private String email;
    private String password;
    private String firstName = "";
    private String lastName = "";
}

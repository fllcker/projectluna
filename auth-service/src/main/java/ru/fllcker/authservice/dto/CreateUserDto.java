package ru.fllcker.authservice.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CreateUserDto {
    private String email;
    private String password;
    private String firstName = "";
    private String lastName = "";
}

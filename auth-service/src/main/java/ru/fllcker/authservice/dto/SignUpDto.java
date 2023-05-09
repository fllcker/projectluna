package ru.fllcker.authservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDto {
    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 6, max = 32, message = "Password should be greater than 6 letters and less than 32 letters!")
    private String password;

    private String firstName = "";

    private String lastName = "";
}

package ru.fllcker.workspacesservice.dto;

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
public class CreateWorkspaceDto {
    @NotNull
    @Size(min = 3, max = 32, message = "Workspace title should be greater than 3 letters and less than 32 letters!")
    private String title;

    @Size(max = 128)
    private String description;

    @Size(max = 32)
    private String type;
}

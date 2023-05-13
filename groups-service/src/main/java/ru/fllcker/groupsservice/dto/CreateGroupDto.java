package ru.fllcker.groupsservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateGroupDto {
    @NotNull
    @Size(min = 2, max = 32)
    private String title;

    @NotNull
    private String workspaceId;
}

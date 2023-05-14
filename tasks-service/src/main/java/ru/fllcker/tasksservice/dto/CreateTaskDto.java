package ru.fllcker.tasksservice.dto;

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
public class CreateTaskDto {
    @NotNull
    @Size(min = 1, max = 64)
    private String title;

    @NotNull
    @Size(max = 256)
    private String description;

    private String deadLine;

    @NotNull
    private String groupId;
}

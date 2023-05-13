package ru.fllcker.groupsservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddingPersonalGroupDto {
    private String workspaceId;
    private String userId;
}

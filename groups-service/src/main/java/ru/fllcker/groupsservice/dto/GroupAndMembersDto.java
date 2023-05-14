package ru.fllcker.groupsservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.fllcker.groupsservice.models.Group;
import ru.fllcker.groupsservice.models.GroupUser;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupAndMembersDto {
    private Group group;
    private GroupUser members;
}

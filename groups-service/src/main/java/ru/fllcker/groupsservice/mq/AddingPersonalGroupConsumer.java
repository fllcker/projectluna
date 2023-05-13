package ru.fllcker.groupsservice.mq;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.fllcker.groupsservice.dto.AddingPersonalGroupDto;
import ru.fllcker.groupsservice.services.GroupsService;

@Service
@RequiredArgsConstructor
public class AddingPersonalGroupConsumer {
    private final GroupsService groupsService;

    @KafkaListener(topics = "addingPersonalGroup", groupId = "group-1")
    public void listenAddingPersonalGroup(AddingPersonalGroupDto message) {
        groupsService.addGroupForUser(message);
    }
}

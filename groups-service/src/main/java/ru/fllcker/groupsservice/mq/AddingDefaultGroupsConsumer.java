package ru.fllcker.groupsservice.mq;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.fllcker.groupsservice.services.GroupsService;

@Service
@RequiredArgsConstructor
public class AddingDefaultGroupsConsumer {
    private final GroupsService groupsService;

    @KafkaListener(topics = "addingDefaultGroups", groupId = "group-1")
    public void listenAddDefaultGroups(String message) {
        System.out.println("[Groups-Service] Received message: " + message);
        groupsService.addDefaultGroups(message);
    }
}

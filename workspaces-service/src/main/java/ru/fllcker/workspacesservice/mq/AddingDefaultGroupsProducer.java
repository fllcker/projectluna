package ru.fllcker.workspacesservice.mq;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddingDefaultGroupsProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void executeAddDefaultGroups(String workspaceId) {
        kafkaTemplate.send("addingDefaultGroups", workspaceId);
    }
}

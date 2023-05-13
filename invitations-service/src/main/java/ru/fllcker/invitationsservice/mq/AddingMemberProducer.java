package ru.fllcker.invitationsservice.mq;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.fllcker.invitationsservice.dto.AddMemberToWorkspaceDto;

@Service
@RequiredArgsConstructor
public class AddingMemberProducer {
    private final KafkaTemplate<String, AddMemberToWorkspaceDto> kafkaTemplate;

    public void executeAddMemberToWorkspace(AddMemberToWorkspaceDto dto) {
        kafkaTemplate.send("addingMemberToWorkspace", dto);
    }
}

package ru.fllcker.invitationsservice.mq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.fllcker.invitationsservice.dto.AddMemberToWorkspaceDto;

@Service
@RequiredArgsConstructor
public class AddingMemberProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper mapper;

    public void executeAddMemberToWorkspace(AddMemberToWorkspaceDto dto) {
        try {
            String message = mapper.writeValueAsString(dto);
            kafkaTemplate.send("addingMemberToWorkspace", message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

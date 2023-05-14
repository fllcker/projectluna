package ru.fllcker.workspacesservice.mq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.fllcker.workspacesservice.dto.AddingPersonalGroupDto;

@Service
@RequiredArgsConstructor
public class AddingPersonalGroupProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper mapper;

    public void executeAddPersonalGroup(AddingPersonalGroupDto dto) {
        try {
            String message = mapper.writeValueAsString(dto);
            kafkaTemplate.send("addingPersonalGroup", message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

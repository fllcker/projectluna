package ru.fllcker.authservice.mq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.fllcker.authservice.dto.CreateUserDto;

@Service
@RequiredArgsConstructor
public class CreateUserProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper mapper;

    public void executeCreateUser(CreateUserDto dto) {
        try {
            String message = mapper.writeValueAsString(dto);
            kafkaTemplate.send("creatingUser", message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

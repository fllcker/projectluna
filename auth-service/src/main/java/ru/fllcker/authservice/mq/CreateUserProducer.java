package ru.fllcker.authservice.mq;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.fllcker.authservice.dto.CreateUserDto;

@Service
@RequiredArgsConstructor
public class CreateUserProducer {
    private final KafkaTemplate<String, CreateUserDto> kafkaTemplate;

    public void executeCreateUser(CreateUserDto message) {
        kafkaTemplate.send("creatingUser", message);
    }
}

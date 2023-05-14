package ru.fllcker.usersservice.mq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.fllcker.usersservice.dto.CreateUserDto;
import ru.fllcker.usersservice.services.UsersService;

@Service
@RequiredArgsConstructor
public class CreateUserConsumer {
    private final UsersService usersService;
    private final ObjectMapper mapper;

    @KafkaListener(topics = "creatingUser", groupId = "users-group")
    public void listenCreatingUser(String message) {
        CreateUserDto dto = null;
        try {
            dto = mapper.readValue(message, CreateUserDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        usersService.create(dto);
    }
}

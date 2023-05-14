package ru.fllcker.usersservice.mq;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.fllcker.usersservice.dto.CreateUserDto;
import ru.fllcker.usersservice.services.UsersService;

@Service
@RequiredArgsConstructor
public class CreateUserConsumer {
    private final UsersService usersService;

    @KafkaListener(topics = "creatingUser", groupId = "users-group")
    public void listenCreatingUser(CreateUserDto createUserDto) {
        usersService.create(createUserDto);
    }
}

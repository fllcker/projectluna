package ru.fllcker.groupsservice.mq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.fllcker.groupsservice.dto.AddingPersonalGroupDto;
import ru.fllcker.groupsservice.services.GroupsService;

@Service
@RequiredArgsConstructor
public class AddingPersonalGroupConsumer {
    private final GroupsService groupsService;
    private final ObjectMapper mapper;

    @KafkaListener(topics = "addingPersonalGroup", groupId = "group-1")
    public void listenAddingPersonalGroup(String message) {
        AddingPersonalGroupDto dto = null;
        try {
            dto = mapper.readValue(message, AddingPersonalGroupDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        groupsService.addGroupForUser(dto);
    }
}

package ru.fllcker.workspacesservice.mq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.fllcker.workspacesservice.dto.AddMemberToWorkspaceDto;
import ru.fllcker.workspacesservice.services.WorkspacesService;

@Service
@RequiredArgsConstructor
public class AddingMemberConsumer {
    private final WorkspacesService workspacesService;
    private final ObjectMapper mapper;

    @KafkaListener(topics = "addingMemberToWorkspace", groupId = "group-1")
    public void listenAddDefaultGroups(String message) {
        try {
            AddMemberToWorkspaceDto dto = mapper.readValue(message, AddMemberToWorkspaceDto.class);
            workspacesService.addMember(dto.getWorkspaceId(), dto.getUserId());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

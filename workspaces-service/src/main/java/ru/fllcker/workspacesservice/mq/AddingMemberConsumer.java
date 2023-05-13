package ru.fllcker.workspacesservice.mq;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.fllcker.workspacesservice.dto.AddMemberToWorkspaceDto;
import ru.fllcker.workspacesservice.services.WorkspacesService;

@Service
@RequiredArgsConstructor
public class AddingMemberConsumer {
    private final WorkspacesService workspacesService;

    @KafkaListener(topics = "addingMemberToWorkspace", groupId = "group-1")
    public void listenAddDefaultGroups(AddMemberToWorkspaceDto message) {
        workspacesService.addMember(message.getWorkspaceId(), message.getUserId());
    }
}

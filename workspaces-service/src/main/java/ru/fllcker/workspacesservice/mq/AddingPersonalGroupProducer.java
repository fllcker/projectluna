package ru.fllcker.workspacesservice.mq;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.fllcker.workspacesservice.dto.AddingPersonalGroupDto;

@Service
@RequiredArgsConstructor
public class AddingPersonalGroupProducer {
    private final KafkaTemplate<String, AddingPersonalGroupDto> kafkaTemplate;

    public void executeAddPersonalGroup(AddingPersonalGroupDto dto) {
        kafkaTemplate.send("addingPersonalGroup", dto);
    }
}

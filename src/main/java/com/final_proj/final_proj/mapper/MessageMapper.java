package com.final_proj.final_proj.mapper;

import com.final_proj.final_proj.dto.messages.CreateMessageDTO;
import com.final_proj.final_proj.entity.Message;
import com.final_proj.final_proj.entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MessageMapper {

    public Message messageFromDto(CreateMessageDTO messageDTO, User sender) {
        return Message.builder()
                .title(messageDTO.title())
                .messageBody(messageDTO.body())
                .sendDate(LocalDateTime.now())
                .sender(sender)
                .build();
    }
}

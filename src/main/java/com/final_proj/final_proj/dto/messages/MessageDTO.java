package com.final_proj.final_proj.dto.messages;


import java.time.LocalDateTime;

public record MessageDTO(
        Long id,
        String title,
        String body,
        LocalDateTime sendTime,
        String senderEmail,

        String messageType
) {}

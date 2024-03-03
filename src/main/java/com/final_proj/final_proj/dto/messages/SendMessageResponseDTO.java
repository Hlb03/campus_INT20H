package com.final_proj.final_proj.dto.messages;

import java.time.LocalDateTime;

public record SendMessageResponseDTO(
   LocalDateTime sendTime,
   String response
) {}

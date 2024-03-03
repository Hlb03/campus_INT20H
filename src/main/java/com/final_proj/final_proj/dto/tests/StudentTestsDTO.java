package com.final_proj.final_proj.dto.tests;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class StudentTestsDTO  {
    private Long id;
    private String subject;
    private String theme;
    private BigDecimal score;
    private LocalDateTime deadline;
}

package com.final_proj.final_proj.dto.tests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TestOptionDTO {
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;

    private Byte correctOption;
}

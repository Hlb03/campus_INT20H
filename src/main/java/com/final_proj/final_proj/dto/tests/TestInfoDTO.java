package com.final_proj.final_proj.dto.tests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TestInfoDTO {
    private Long id;
    private String theme;
    private LocalDateTime deadline;
    private Long subjectId;
    private Long assignedGroupId;

    private List<TestOptionDTO> options;
}

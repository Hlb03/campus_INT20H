package com.final_proj.final_proj.dto.tests;

import java.math.BigDecimal;

public record PassTestRequestDTO(
        Long testId,
        BigDecimal score
) {}

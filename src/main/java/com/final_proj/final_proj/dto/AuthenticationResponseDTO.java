package com.final_proj.final_proj.dto;

public record AuthenticationResponseDTO(
        String token,
        String role
){ }

package com.final_proj.final_proj.dto;

import com.final_proj.final_proj.entity.Role;

public record RegistrationRequestDTO(
        String email,
        String firstName,
        String lastName,
        String middleName,
        String password,
        Role role
) {}

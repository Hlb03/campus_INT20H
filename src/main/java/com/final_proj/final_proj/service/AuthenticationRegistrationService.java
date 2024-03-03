package com.final_proj.final_proj.service;

import com.final_proj.final_proj.dto.AuthRequestDTO;
import com.final_proj.final_proj.dto.AuthenticationResponseDTO;
import com.final_proj.final_proj.dto.RegistrationRequestDTO;
import com.final_proj.final_proj.exceptions.AuthenticationException;
import com.final_proj.final_proj.exceptions.LoginAlreadyRegisteredException;

public interface AuthenticationRegistrationService {

    Long registerUser(RegistrationRequestDTO requestDTO) throws LoginAlreadyRegisteredException;

    AuthenticationResponseDTO authenticateUser(AuthRequestDTO requestDTO) throws AuthenticationException;
}

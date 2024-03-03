package com.final_proj.final_proj.controller;


import com.final_proj.final_proj.dto.AuthRequestDTO;
import com.final_proj.final_proj.dto.AuthenticationResponseDTO;
import com.final_proj.final_proj.dto.RegistrationRequestDTO;
import com.final_proj.final_proj.exceptions.AuthenticationException;
import com.final_proj.final_proj.exceptions.LoginAlreadyRegisteredException;
import com.final_proj.final_proj.service.AuthenticationRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class AuthAndRegistrationController {

    private final AuthenticationRegistrationService authRegService;

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public Long registerUser(@RequestBody RegistrationRequestDTO registrationRequest) {
        try {
            return authRegService.registerUser(registrationRequest);
        } catch (LoginAlreadyRegisteredException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @PostMapping("/auth")
    @ResponseStatus(HttpStatus.OK)
    public AuthenticationResponseDTO authenticateUser(@RequestBody AuthRequestDTO authRequest) {
        try {
            return authRegService.authenticateUser(authRequest);
        } catch (AuthenticationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}

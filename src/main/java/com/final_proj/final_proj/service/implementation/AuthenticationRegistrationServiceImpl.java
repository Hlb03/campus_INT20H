package com.final_proj.final_proj.service.implementation;

import com.final_proj.final_proj.dto.AuthRequestDTO;
import com.final_proj.final_proj.dto.AuthenticationResponseDTO;
import com.final_proj.final_proj.dto.RegistrationRequestDTO;
import com.final_proj.final_proj.entity.User;
import com.final_proj.final_proj.exceptions.AuthenticationException;
import com.final_proj.final_proj.exceptions.LoginAlreadyRegisteredException;
import com.final_proj.final_proj.repository.UserRepository;
import com.final_proj.final_proj.service.AuthenticationRegistrationService;
import com.final_proj.final_proj.utils.JsonTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationRegistrationServiceImpl implements AuthenticationRegistrationService {

    private final AuthenticationManager manager;
    private final UserDetailsService userDetailsService;
    private final JsonTokenUtil jsonToken;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Override
    public Long registerUser(RegistrationRequestDTO registrationRequest) throws LoginAlreadyRegisteredException {
        if (userRepository.findStudentByEmail(registrationRequest.email()).isPresent()) {
            log.info(String.format("Failed to register user with login %s%n", registrationRequest.email()));
            throw new LoginAlreadyRegisteredException(
                    String.format("Login %s is already taken. Please, try another one.", registrationRequest.email())
            );
        }

        log.info("Saving user with the following credentials: {}", registrationRequest);
        return userRepository.save(
                User.builder()
                        .firstName(registrationRequest.firstName())
                        .lastName(registrationRequest.lastName())
                        .middleName(registrationRequest.middleName())
                        .email(registrationRequest.email())
                        .password(encoder.encode(registrationRequest.password()))
                        .role(registrationRequest.role())
                        .build()
        ).getId();
    }

    @Override
    public AuthenticationResponseDTO authenticateUser(AuthRequestDTO requestDTO) throws AuthenticationException {
        log.info("User credentials: " + requestDTO);
        try {
            manager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestDTO.getEmail(),
                            requestDTO.getPassword()
                    )
            );
            log.info("Authorizing user");
        } catch (BadCredentialsException e) {
            log.info(String.format("Invalid credentials for user %s authentication%n", requestDTO.getEmail()));
            throw new AuthenticationException("Invalid credentials for user authentication. Please check for mistakes presence.");
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(requestDTO.getEmail());
        return new AuthenticationResponseDTO(jsonToken.generateToken(userDetails),
                userDetails.getAuthorities().toString().replace("[", "").replace("]", ""));
    }
}

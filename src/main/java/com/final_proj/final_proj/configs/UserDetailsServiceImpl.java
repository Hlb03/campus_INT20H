package com.final_proj.final_proj.configs;

import com.final_proj.final_proj.entity.User;
import com.final_proj.final_proj.service.implementation.UserServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserServiceImpl userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUserByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("USER WITH LOGIN " + username + " WASN'T FOUND"));
        log.info("AUTHORIZING USER WITH PARAMS: " + user.getEmail());

        return org.springframework.security.core.userdetails.User
                .builder()
                .username(username)
                .password(user.getPassword())
                .authorities(user.getRole().getAuthority())
                .disabled(false)
                .build();
    }
}

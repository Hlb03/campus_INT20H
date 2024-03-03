package com.final_proj.final_proj.configs;

import com.final_proj.final_proj.entity.Role;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig {

    private final BCryptPasswordEncoder encoder;
    private final UserDetailsServiceImpl userService;
    private final JwtFilter jwtFilter;

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests( requests -> requests

                                .requestMatchers(HttpMethod.GET, "/tests/*").hasRole(Role.STUDENT.name())
                                .requestMatchers(HttpMethod.POST, "/tests/process").hasRole(Role.STUDENT.name())
                                .requestMatchers(HttpMethod.POST, "/tests/create").hasRole(Role.TEACHER.name())

                                .requestMatchers(HttpMethod.GET, "/messages/all").hasRole(Role.STUDENT.name())
                                .requestMatchers(HttpMethod.POST, "/messages/**").hasAnyRole(Role.TEACHER.name(), Role.ADMIN.name())

                                .requestMatchers(HttpMethod.PUT, "/update/*").hasAnyRole(Role.TEACHER.name(), Role.ADMIN.name())
                                .requestMatchers(HttpMethod.GET, "/user/info").hasAnyRole(Role.STUDENT.name(), Role.TEACHER.name(), Role.ADMIN.name())
                                .requestMatchers(HttpMethod.GET, "/students/all").hasAnyRole(Role.ADMIN.name(), Role.TEACHER.name())
                                .requestMatchers(HttpMethod.POST, "/registration").hasRole(Role.ADMIN.name())
                                .requestMatchers(HttpMethod.POST, "/auth").permitAll()

                                .requestMatchers("/error").permitAll() // Spring Boot 3.0 also applies security to error dispatches
                )
                .authenticationProvider(daoAuthenticationProvider())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    protected DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(encoder);
        daoAuthenticationProvider.setUserDetailsService(userService);
        return daoAuthenticationProvider;
    }

    @Bean
    protected AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
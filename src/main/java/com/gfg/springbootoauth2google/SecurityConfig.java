package com.gfg.springbootoauth2google;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/login**").permitAll() // Allow unauthenticated access to home and login pages
                        .anyRequest().authenticated() // Require authentication for all other requests
                )
                .oauth2Login(oauth2 -> oauth2
                        .defaultSuccessUrl("/dashboard", true) // Redirect to dashboard after successful login
                );
        return http.build();
    }
}
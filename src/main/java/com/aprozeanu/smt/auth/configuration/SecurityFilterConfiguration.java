package com.aprozeanu.smt.auth.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityFilterConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthProvider authProvider) throws Exception {
        http.authorizeHttpRequests((authorize) -> authorize
                .requestMatchers(HttpMethod.GET, "/auth/session-token").authenticated()
                .requestMatchers(HttpMethod.POST, "/auth/session-token").authenticated()
                .anyRequest().permitAll())
            .httpBasic(withDefaults())
            .authenticationProvider(authProvider);
        return http.build();
    }
}

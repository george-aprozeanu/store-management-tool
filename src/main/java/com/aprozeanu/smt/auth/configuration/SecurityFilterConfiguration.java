package com.aprozeanu.smt.auth.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityFilterConfiguration {

    @Bean
    @Order(1)
    public SecurityFilterChain sessionTokenFilterChain(HttpSecurity http, JwtTokenAuthProvider jwtTokenAuthProvider) throws Exception {
        http.securityMatcher("/api/v1/auth/session-token")
            .authorizeHttpRequests(a -> a.anyRequest().authenticated())
            .authenticationProvider(jwtTokenAuthProvider);
        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain authFilterChain(HttpSecurity http, BasicAuthProvider authProvider) throws Exception {
        http.securityMatcher("/**").authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
            .httpBasic(Customizer.withDefaults())
            .authenticationProvider(authProvider);
        return http.build();
    }
}

package com.aprozeanu.smt.auth.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityFilterConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthProvider authProvider,
                                           JwtTokenFilter jwtTokenFilter) throws Exception {
        http.authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated())
            .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
            .httpBasic(config -> config.realmName("com.aprozeanu.smt")).authenticationProvider(authProvider);
        return http.build();
    }
}

package com.aprozeanu.smt.auth.configuration;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @GetMapping("/session-token")
    public AuthResponse getSessionToken() {
        return new AuthController.AuthResponse("cici");
    }

    public record AuthResponse(String sessionToken) {

    }
}

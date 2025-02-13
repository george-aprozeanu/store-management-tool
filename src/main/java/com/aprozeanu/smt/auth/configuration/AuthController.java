package com.aprozeanu.smt.auth.configuration;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @PostMapping("/session-token")
    public AuthResponse getSessionToken() {
        return new AuthController.AuthResponse("cici");
    }

    public record AuthResponse(String sessionToken) {

    }
}

package com.aprozeanu.smt.auth.configuration;

import com.aprozeanu.smt.auth.service.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BasicAuthProvider implements AuthenticationProvider {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public BasicAuthProvider(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("basic auth for: " + authentication);
        return authentication;
//        if (authentication.isAuthenticated()) return authentication;
//        if (authentication.getPrincipal() != null) return authentication;
//        String username = authentication.getName();
//        String password = (String) authentication.getCredentials();
//        UserService.UserAndRoles userAndRoles = userService.getUser(username)
//            .filter($userAndRoles -> passwordEncoder.matches(password, $userAndRoles.user().getPassword()))
//            .orElseThrow(() -> new UsernameNotFoundException("username/password"));
//        return new AuthenticationImpl(userAndRoles.user());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        System.out.println("class check: " + authentication.getCanonicalName());
        return Authentication.class.isAssignableFrom(authentication);
    }
}

package com.aprozeanu.smt.auth.configuration;

import com.aprozeanu.smt.auth.model.Role;
import com.aprozeanu.smt.auth.model.User;
import com.aprozeanu.smt.auth.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AuthProviderTest {

    @Mock
    private UserService userService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthProvider authProvider;

    private AutoCloseable mocks;

    @BeforeEach
    void setUp() {
        this.mocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        this.mocks.close();
    }

    @Test
    void authenticateWithValidCredentials() {
        String username = "validUser";
        String password = "validPassword";
        User user = new User();
        user.setUsername(username);
        user.setPassword("encodedPassword");
        user.setRoles(Set.of(new Role("Consumer"), new Role("Administrator")));

        Mockito.when(userService.getUser(username)).thenReturn(Optional.of(user));
        Mockito.when(passwordEncoder.matches(password, user.getPassword())).thenReturn(true);

        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
        Authentication result = authProvider.authenticate(authentication);

        assertNotNull(result);
        assertEquals(username, result.getName());
    }

    @Test
    void authenticateWithInvalidPassword() {
        String username = "validUser";
        String password = "invalidPassword";
        User user = new User();
        user.setPassword("encodedPassword");

        Mockito.when(userService.getUser(username)).thenReturn(Optional.of(user));
        Mockito.when(passwordEncoder.matches(password, user.getPassword())).thenReturn(false);

        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);

        assertThrows(UsernameNotFoundException.class, () -> authProvider.authenticate(authentication));
    }

    @Test
    void authenticateWithNonExistentUser() {
        String username = "nonExistentUser";
        String password = "anyPassword";

        Mockito.when(userService.getUser(username)).thenReturn(Optional.empty());

        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);

        assertThrows(UsernameNotFoundException.class, () -> authProvider.authenticate(authentication));
    }
}

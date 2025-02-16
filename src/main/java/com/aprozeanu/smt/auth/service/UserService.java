package com.aprozeanu.smt.auth.service;

import com.aprozeanu.smt.auth.model.User;
import com.aprozeanu.smt.auth.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUser(String username) {
        return userRepository.getFirstByEnabledAndUsername(true, username);
    }
}

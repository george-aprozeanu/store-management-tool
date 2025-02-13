package com.aprozeanu.smt.auth.service;

import com.aprozeanu.smt.auth.model.Role;
import com.aprozeanu.smt.auth.model.User;
import com.aprozeanu.smt.auth.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public Optional<UserAndRoles> getUser(String username) {
        var maybeUser = userRepository.getFirstByEnabledAndUsername(true, username);
        return maybeUser.map(user -> new UserAndRoles(user,
            user.getRoles().stream().map(Role::getName).toList()));
    }

    public record UserAndRoles(User user, List<String> role) {
    }
}

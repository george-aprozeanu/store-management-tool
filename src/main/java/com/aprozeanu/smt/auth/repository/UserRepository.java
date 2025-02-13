package com.aprozeanu.smt.auth.repository;

import com.aprozeanu.smt.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> getFirstByEnabledAndUsername(Boolean enabled, String username);
}

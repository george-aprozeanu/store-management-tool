package com.aprozeanu.smt.auth.repository;

import com.aprozeanu.smt.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

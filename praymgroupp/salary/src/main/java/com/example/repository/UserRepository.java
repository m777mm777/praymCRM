package com.example.repository;

import com.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLastName(String lastName);
    Optional<User> findByRole(String role);
    Optional<User> findByEmailAndPassword(String email, String password);
    List<User> findAllByRole(String role);
    List<User> findByDismissedAndRoleNot(Boolean dismissed, String role);
}

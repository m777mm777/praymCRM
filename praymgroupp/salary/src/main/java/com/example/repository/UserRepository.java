package com.example.repository;

import com.example.model.Salary;
import com.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID>, JpaSpecificationExecutor<User> {
    Optional<User> findByLastName(String lastName);
    Optional<User> findByRole(String role);
    Optional<User> findByPhoneAndPassword(Long phone, String password);
    List<User> findAllByRoleIn(List<String> roles);
    List<User> findByDismissedAndRoleNot(Boolean dismissed, String role);
    List<User> findAllByOwnerIdAndDismissed(Long ownerId, Boolean dismissed);
    List<User> findAllByDismissed(Boolean dismissed);
}

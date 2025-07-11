package com.example.userpermissionsystem.user.repository;

import com.example.userpermissionsystem.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    // ✅ Add this line below
    boolean existsByUsername(String username);
}

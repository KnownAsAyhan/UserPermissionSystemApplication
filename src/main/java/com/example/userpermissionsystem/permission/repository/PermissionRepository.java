package com.example.userpermissionsystem.permission.repository;

import com.example.userpermissionsystem.permission.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Optional<Permission> findByName(String name);
}

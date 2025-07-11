package com.example.userpermissionsystem.config;

import com.example.userpermissionsystem.permission.entity.Permission;
import com.example.userpermissionsystem.permission.repository.PermissionRepository;
import com.example.userpermissionsystem.user.entity.User;
import com.example.userpermissionsystem.user.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataLoader {

    private final UserRepository userRepository;
    private final PermissionRepository permissionRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        // Create permissions if not exist
        Permission adminPermission = permissionRepository
                .findByName("ADMIN")
                .orElseGet(() -> permissionRepository.save(new Permission(null, "ADMIN")));

        Permission readPermission = permissionRepository
                .findByName("READ_PRIVILEGES")
                .orElseGet(() -> permissionRepository.save(new Permission(null, "READ_PRIVILEGES")));

        Permission writePermission = permissionRepository
                .findByName("WRITE_PRIVILEGES")
                .orElseGet(() -> permissionRepository.save(new Permission(null, "WRITE_PRIVILEGES")));

        // Create admin user if not exists
        if (!userRepository.existsByUsername("admin")) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setPermissions(Set.of(adminPermission));
            userRepository.save(admin);
        }
    }
}

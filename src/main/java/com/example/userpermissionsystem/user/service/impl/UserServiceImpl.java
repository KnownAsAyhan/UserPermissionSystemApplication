package com.example.userpermissionsystem.user.service.impl;

import com.example.userpermissionsystem.exception.UserAlreadyExistsException;
import com.example.userpermissionsystem.permission.entity.Permission;
import com.example.userpermissionsystem.permission.repository.PermissionRepository;
import com.example.userpermissionsystem.user.dto.UserRequest;
import com.example.userpermissionsystem.user.dto.UserResponse;
import com.example.userpermissionsystem.user.entity.User;
import com.example.userpermissionsystem.user.mapper.UserMapper;
import com.example.userpermissionsystem.user.repository.UserRepository;
import com.example.userpermissionsystem.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PermissionRepository permissionRepository; // Inject this
    private final UserMapper userMapper;

    @Override
    public UserResponse create(UserRequest userRequest) {
        if (userRepository.findByUsername(userRequest.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("Username already exists!");
        }

        // Convert permission names (strings) to Permission entities from DB
        Set<Permission> permissions = userRequest.getPermissions().stream()
                .map(name -> permissionRepository.findByName(name)
                        .orElseThrow(() -> new RuntimeException("Permission not found: " + name)))
                .collect(Collectors.toSet());

        // Map UserRequest DTO to User entity (without permissions yet)
        User user = userMapper.toEntity(userRequest);

        // Set the fetched Permission entities to the user before saving
        user.setPermissions(permissions);

        // Save the user with valid permissions
        user = userRepository.save(user);

        // Return mapped UserResponse
        return userMapper.toResponse(user);
    }

    @Override
    public List<UserResponse> getAll() {
        return userRepository.findAll().stream()
                .map(user -> {
                    UserResponse response = userMapper.toResponse(user);
                    response.setPermissions(userMapper.mapPermissions(user.getPermissions()));
                    return response;
                })
                .collect(Collectors.toList());
    }

    // New: Get user by id
    @Override
    public UserResponse getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        UserResponse response = userMapper.toResponse(user);
        response.setPermissions(userMapper.mapPermissions(user.getPermissions()));
        return response;
    }

    // New: Update user by id
    @Override
    public UserResponse update(Long id, UserRequest userRequest) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        user.setUsername(userRequest.getUsername());

        Set<Permission> permissions = userRequest.getPermissions().stream()
                .map(name -> permissionRepository.findByName(name)
                        .orElseThrow(() -> new RuntimeException("Permission not found: " + name)))
                .collect(Collectors.toSet());

        user.setPermissions(permissions);

        user = userRepository.save(user);
        return userMapper.toResponse(user);
    }

    // New: Delete user by id
    @Override
    public void deleteById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }
}

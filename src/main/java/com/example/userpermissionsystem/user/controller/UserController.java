package com.example.userpermissionsystem.user.controller;

import com.example.userpermissionsystem.user.dto.UserRequest;
import com.example.userpermissionsystem.user.dto.UserResponse;
import com.example.userpermissionsystem.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> create(@Valid @RequestBody UserRequest userRequest) {
        UserResponse response = userService.create(userRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    // New: Get user by id
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@Valid @PathVariable Long id) {
        UserResponse response = userService.getById(id);
        return ResponseEntity.ok(response);
    }

    // New: Update user by id
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@Valid @PathVariable Long id, @Valid @RequestBody UserRequest userRequest) {
        UserResponse response = userService.update(id, userRequest);
        return ResponseEntity.ok(response);
    }

    // New: Delete user by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Valid @PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

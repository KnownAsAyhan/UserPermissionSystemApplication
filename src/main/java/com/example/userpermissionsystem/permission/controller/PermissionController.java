package com.example.userpermissionsystem.permission.controller;

import com.example.userpermissionsystem.permission.dto.PermissionRequest;
import com.example.userpermissionsystem.permission.dto.PermissionResponse;
import com.example.userpermissionsystem.permission.service.PermissionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permissions")
@RequiredArgsConstructor
public class PermissionController {

    private final PermissionService service;

    @PostMapping
    public ResponseEntity<PermissionResponse> create(@RequestBody @Valid PermissionRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping
    public ResponseEntity<List<PermissionResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PermissionResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PermissionResponse> update(@PathVariable Long id,
                                                     @RequestBody @Valid PermissionRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

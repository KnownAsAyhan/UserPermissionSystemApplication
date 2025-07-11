package com.example.userpermissionsystem.permission.service;

import com.example.userpermissionsystem.permission.dto.PermissionRequest;
import com.example.userpermissionsystem.permission.dto.PermissionResponse;

import java.util.List;

public interface PermissionService {
    PermissionResponse create(PermissionRequest request);
    List<PermissionResponse> getAll();
    PermissionResponse getById(Long id);
    PermissionResponse update(Long id, PermissionRequest request);
    void delete(Long id);
}

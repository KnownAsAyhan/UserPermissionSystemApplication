package com.example.userpermissionsystem.permission.service.impl;

import com.example.userpermissionsystem.exception.PermissionNotFoundException;
import com.example.userpermissionsystem.permission.dto.PermissionRequest;
import com.example.userpermissionsystem.permission.dto.PermissionResponse;
import com.example.userpermissionsystem.permission.entity.Permission;
import com.example.userpermissionsystem.permission.mapper.PermissionMapper;
import com.example.userpermissionsystem.permission.repository.PermissionRepository;
import com.example.userpermissionsystem.permission.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository repository;
    private final PermissionMapper mapper;

    @Override
    public PermissionResponse create(PermissionRequest request) {
        Permission permission = mapper.toEntity(request);
        return mapper.toDto(repository.save(permission));
    }

    @Override
    public List<PermissionResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public PermissionResponse getById(Long id) {
        Permission permission = repository.findById(id)
                .orElseThrow(() -> new PermissionNotFoundException("Permission not found with id: " + id));
        return mapper.toDto(permission);
    }

    @Override
    public PermissionResponse update(Long id, PermissionRequest request) {
        Permission existing = repository.findById(id)
                .orElseThrow(() -> new PermissionNotFoundException("Permission not found with id: " + id));

        existing.setName(request.name());
        return mapper.toDto(repository.save(existing));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}

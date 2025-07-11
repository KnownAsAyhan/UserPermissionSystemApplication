package com.example.userpermissionsystem.group.service.impl;

import com.example.userpermissionsystem.exception.ResourceNotFoundException;
import com.example.userpermissionsystem.group.dto.GroupRequest;
import com.example.userpermissionsystem.group.dto.GroupResponse;
import com.example.userpermissionsystem.group.entity.Group;
import com.example.userpermissionsystem.group.mapper.GroupMapper;
import com.example.userpermissionsystem.group.repository.GroupRepository;
import com.example.userpermissionsystem.group.service.GroupService;
import com.example.userpermissionsystem.permission.entity.Permission;
import com.example.userpermissionsystem.permission.repository.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final PermissionRepository permissionRepository;
    private final GroupMapper groupMapper;

    @Override
    public GroupResponse create(GroupRequest request) {
        // Optional: check for duplicate group name
        if (groupRepository.existsByName(request.getName())) {
            throw new RuntimeException("Group name already exists");
        }

        // Map DTO to entity
        Group group = groupMapper.toEntity(request);

        // Map permission names to Permission entities
        Set<Permission> permissions = request.getPermissions().stream()
                .map(name -> permissionRepository.findByName(name)
                        .orElseThrow(() -> new ResourceNotFoundException("Permission not found: " + name)))
                .collect(Collectors.toSet());

        group.setPermissions(permissions);

        Group savedGroup = groupRepository.save(group);
        return groupMapper.toDto(savedGroup);
    }

    @Override
    public GroupResponse getById(Long id) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Group not found with id: " + id));
        return groupMapper.toDto(group);
    }

    @Override
    public java.util.List<GroupResponse> getAll() {
        return groupRepository.findAll()
                .stream()
                .map(groupMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public GroupResponse update(Long id, GroupRequest request) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Group not found with id: " + id));

        group.setName(request.getName());

        Set<Permission> permissions = request.getPermissions().stream()
                .map(name -> permissionRepository.findByName(name)
                        .orElseThrow(() -> new ResourceNotFoundException("Permission not found: " + name)))
                .collect(Collectors.toSet());

        group.setPermissions(permissions);

        Group updated = groupRepository.save(group);
        return groupMapper.toDto(updated);
    }

    @Override
    public void delete(Long id) {
        if (!groupRepository.existsById(id)) {
            throw new ResourceNotFoundException("Group not found with id: " + id);
        }
        groupRepository.deleteById(id);
    }
}

package com.example.userpermissionsystem.permission.mapper;

import com.example.userpermissionsystem.permission.dto.PermissionRequest;
import com.example.userpermissionsystem.permission.dto.PermissionResponse;
import com.example.userpermissionsystem.permission.entity.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PermissionMapper {

    PermissionResponse toDto(Permission permission);

    Permission toEntity(PermissionRequest request);
}

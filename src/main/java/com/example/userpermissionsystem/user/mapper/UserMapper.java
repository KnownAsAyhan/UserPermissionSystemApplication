package com.example.userpermissionsystem.user.mapper;

import com.example.userpermissionsystem.permission.entity.Permission;
import com.example.userpermissionsystem.user.dto.UserRequest;
import com.example.userpermissionsystem.user.dto.UserResponse;
import com.example.userpermissionsystem.user.entity.User;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    @Mapping(target = "password", source = "password")
    @Mapping(target = "username", source = "username")
    User toEntity(UserRequest userRequest);

    // Explicitly map permissions collection to a list of strings in response
    @Mapping(target = "permissions", expression = "java(mapPermissions(user.getPermissions()))")
    UserResponse toResponse(User user);

    // Utility method to convert Set<Permission> to List<String> of permission names
    default List<String> mapPermissions(Collection<Permission> permissions) {
        if (permissions == null) return List.of();
        return permissions.stream()
                .map(Permission::getName)
                .collect(Collectors.toList());
    }

    // Utility method to convert List<String> permission names to Set<Permission> entities
    default Set<Permission> mapPermissionEntities(List<String> permissionNames) {
        if (permissionNames == null) return Set.of();
        return permissionNames.stream()
                .map(name -> {
                    Permission permission = new Permission();
                    permission.setName(name);
                    return permission;
                })
                .collect(Collectors.toSet());
    }
}

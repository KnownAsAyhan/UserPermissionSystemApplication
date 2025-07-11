package com.example.userpermissionsystem.group.mapper;

import com.example.userpermissionsystem.group.dto.GroupRequest;
import com.example.userpermissionsystem.group.dto.GroupResponse;
import com.example.userpermissionsystem.group.entity.Group;
import com.example.userpermissionsystem.permission.entity.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GroupMapper {

    @Mapping(target = "permissions", source = "permissions", qualifiedByName = "permissionNamesToEntities")
    Group toEntity(GroupRequest request);

    @Mapping(target = "permissions", source = "permissions", qualifiedByName = "permissionsToNames")
    GroupResponse toDto(Group group);  // renamed from toResponse to toDto

    @Named("permissionNamesToEntities")
    default Set<Permission> mapPermissionNamesToEntities(Set<String> names) {
        return names.stream()
                .map(name -> Permission.builder().name(name).build())
                .collect(Collectors.toSet());
    }

    @Named("permissionsToNames")
    default Set<String> mapPermissionsToNames(Set<Permission> permissions) {
        return permissions.stream()
                .map(Permission::getName)
                .collect(Collectors.toSet());
    }
}

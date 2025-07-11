package com.example.userpermissionsystem.permission.dto;

import jakarta.validation.constraints.NotBlank;

public record PermissionRequest(
        @NotBlank(message = "{permission.name.required}")
        String name
) {}

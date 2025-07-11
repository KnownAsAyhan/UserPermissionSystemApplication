package com.example.userpermissionsystem.permission.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissionResponse {
    private Long id;
    private String name;
}

package com.example.userpermissionsystem.group.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroupRequest {
    private String name;
    private Set<String> permissions;
}

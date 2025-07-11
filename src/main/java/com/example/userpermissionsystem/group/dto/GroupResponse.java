package com.example.userpermissionsystem.group.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroupResponse {

    private Long id;
    private String name;
    private Set<String> permissions;  // permission names
}

package com.example.userpermissionsystem.user.dto;

import java.util.List;

public class UserResponse {
    private Long id;
    private String username;
    private List<String> permissions;

    public UserResponse() {
    }

    public UserResponse(Long id, String username, List<String> permissions) {
        this.id = id;
        this.username = username;
        this.permissions = permissions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }
}

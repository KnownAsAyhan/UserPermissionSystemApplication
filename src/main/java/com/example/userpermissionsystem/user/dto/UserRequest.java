package com.example.userpermissionsystem.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {

    @NotBlank(message = "{user.username.notblank}")
    @Size(min = 3, max = 50, message = "{user.username.size}")
    private String username;

    @NotBlank(message = "{user.password.notblank}")
    @Size(min = 6, max = 100, message = "{user.password.size}")
    private String password;

    @NotNull(message = "{user.permissions.notnull}")
    private List<String> permissions; // permission names list
}

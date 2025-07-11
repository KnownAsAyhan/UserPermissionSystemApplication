package com.example.userpermissionsystem.user.service;

import com.example.userpermissionsystem.user.dto.UserRequest;
import com.example.userpermissionsystem.user.dto.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse create(UserRequest userRequest);

    List<UserResponse> getAll();

    UserResponse getById(Long id);

    UserResponse update(Long id, UserRequest userRequest);

    void deleteById(Long id);
}

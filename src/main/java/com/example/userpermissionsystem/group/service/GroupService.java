package com.example.userpermissionsystem.group.service;

import com.example.userpermissionsystem.group.dto.GroupRequest;
import com.example.userpermissionsystem.group.dto.GroupResponse;

import java.util.List;

public interface GroupService {
    GroupResponse create(GroupRequest request);
    List<GroupResponse> getAll();
    GroupResponse getById(Long id);
    GroupResponse update(Long id, GroupRequest request);
    void delete(Long id);
}

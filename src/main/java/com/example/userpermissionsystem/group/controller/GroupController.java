package com.example.userpermissionsystem.group.controller;

import com.example.userpermissionsystem.group.dto.GroupRequest;
import com.example.userpermissionsystem.group.dto.GroupResponse;
import com.example.userpermissionsystem.group.service.GroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @PostMapping
    public ResponseEntity<GroupResponse> create(@Valid @RequestBody GroupRequest request) {
        GroupResponse response = groupService.create(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<GroupResponse>> getAll() {
        List<GroupResponse> groups = groupService.getAll();
        return ResponseEntity.ok(groups);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupResponse> getById(@PathVariable Long id) {
        GroupResponse response = groupService.getById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupResponse> update(@PathVariable Long id, @Valid @RequestBody GroupRequest request) {
        GroupResponse response = groupService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        groupService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

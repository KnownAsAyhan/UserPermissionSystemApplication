package com.example.userpermissionsystem.exception;

public class PermissionNotFoundException extends RuntimeException {
    public PermissionNotFoundException(String message) {
        super(message);
    }
}

package com.example.userpermissionsystem.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    // Validation errors - field specific with localization
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex, Locale locale) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String localizedErrorMessage = messageSource.getMessage(error, locale);
            errors.put(fieldName, localizedErrorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    // Specific exceptions with custom status and messages
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handleUserAlreadyExists(UserAlreadyExistsException ex, Locale locale) {
        String message = messageSource.getMessage("user.exists", null, ex.getMessage(), locale);
        return new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(PermissionNotFoundException.class)
    public ResponseEntity<String> handlePermissionNotFound(PermissionNotFoundException ex, Locale locale) {
        String message = messageSource.getMessage("permission.notfound", null, ex.getMessage(), locale);
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException ex, Locale locale) {
        String message = messageSource.getMessage("resource.notfound", null, ex.getMessage(), locale);
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    // Generic runtime exceptions fallback
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex, Locale locale) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}

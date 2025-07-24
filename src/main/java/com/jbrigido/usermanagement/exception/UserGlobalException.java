package com.jbrigido.usermanagement.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Global exception handler for the user api
 * This is a spring @ControllerAdvice
 */
@ControllerAdvice
public class UserGlobalException {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> userNotFound(UserNotFound ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> argumentNotValid(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().
                getFieldErrors()
                .stream()
                .map(e -> e.getField() + " : " + e.getDefaultMessage())
                .collect(Collectors.toList());
        return ResponseEntity.badRequest().body(errors);

    }

}

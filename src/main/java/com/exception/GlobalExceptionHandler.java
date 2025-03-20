package com.exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolationException;

/**
 * Global exception handler to manage different types of exceptions thrown in the application.
 * 
 * This class uses `@RestControllerAdvice` to handle specific exceptions globally and send 
 * appropriate responses to the client.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles `BlogNotFoundException` and returns a `404 Not Found` response.
     * 
     * @param ex The exception that occurred.
     * @return A ResponseEntity with the exception message and a 404 status.
     */
    @ExceptionHandler(BlogNotFoundException.class)
    public ResponseEntity<String> handleBlogNotFoundException(BlogNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles `CommentNotFoundException` and returns a `404 Not Found` response.
     * 
     * @param ex The exception that occurred.
     * @return A ResponseEntity with the exception message and a 404 status.
     */
    @ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity<String> handleCommentNotFoundException(CommentNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles `IllegalArgumentException` and returns a `400 Bad Request` response.
     * 
     * @param ex The exception that occurred.
     * @return A ResponseEntity with the exception message and a 400 status.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles validation errors during method argument binding.
     * 
     * @param ex The exception that occurred.
     * @return A ResponseEntity with the validation errors and a 400 status.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles `InvalidBlogIdException` and returns a `400 Bad Request` response.
     * 
     * @param ex The exception that occurred.
     * @return A ResponseEntity with the exception message and a 400 status.
     */
    @ExceptionHandler(InvalidBlogIdException.class)
    public ResponseEntity<String> handleInvalidBlogIdException(InvalidBlogIdException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    /**
     * Handles `ConstraintViolationException` and returns a `400 Bad Request` response.
     * 
     * @param ex The exception that occurred.
     * @return A ResponseEntity with the constraint violations and a 400 status.
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleConstraintViolationException(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(violation -> {
            errors.put(violation.getPropertyPath().toString(), violation.getMessage());
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles `CustomException` and returns a `404 Not Found` response.
     * 
     * @param ex The exception that occurred.
     * @return A ResponseEntity with the exception message and a 404 status.
     */
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleCustomException(CustomException ex) {
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());
        return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
    }
}

package com.exception;

/**
 * Custom exception class for handling "comment not found" errors.
 * 
 * This exception is thrown when a requested comment is not found in the database.
 */
public class CommentNotFoundException extends RuntimeException {
    
    /**
     * Constructor for `CommentNotFoundException` that accepts a custom error message.
     * 
     * @param message The error message to be passed to the exception.
     */
    public CommentNotFoundException(String message) {
        super(message);
    }
}

package com.exception;

/**
 * Custom exception class for handling "blog not found" errors.
 * 
 * This exception is thrown when a requested blog is not found in the database.
 */
public class BlogNotFoundException extends RuntimeException {
    
    /**
     * Constructor for `BlogNotFoundException` that accepts a custom error message.
     * 
     * @param message The error message to be passed to the exception.
     */
    public BlogNotFoundException(String message) {
        super(message);
    }
}

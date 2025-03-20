package com.exception;

/**
 * Custom exception class for handling general exceptions.
 * 
 * This exception is used to handle specific error cases in the application where a custom error message is needed.
 */
public class CustomException extends RuntimeException {
    
    /**
     * Constructor for `CustomException` that accepts a custom error message.
     * 
     * @param message The error message to be passed to the exception.
     */
    public CustomException(String message) {
        super(message);
    }
}

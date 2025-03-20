package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main entry point of the Spring Boot application.
 * 
 * This class initializes and runs the Spring Boot application.
 * The `@SpringBootApplication` annotation triggers the auto-configuration
 * and component scanning for the application.
 */
@SpringBootApplication
public class BlogMain {
    
    /**
     * The main method to launch the Spring Boot application.
     * 
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(BlogMain.class, args);
    }
}

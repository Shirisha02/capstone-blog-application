package com.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object (DTO) for Blog.
 * 
 * This class is used to transfer blog data between layers (e.g., service and controller).
 * It includes validation annotations to ensure that the title and content are not blank 
 * and adhere to the specified size constraints.
 */
public class BlogDTO {

    private Long id;

    @NotBlank
    @Size(min = 3, max = 100)
    private String title;  // The title of the blog, must not be blank and between 3-100 characters.

    @NotBlank
    @Size(min = 3, max = 200)
    private String content;  // The content of the blog, must not be blank and between 3-200 characters.
    
    private LocalDateTime createdAt;  // The timestamp of when the blog was created.

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    // Constructor with parameters
    public BlogDTO(Long id, @NotBlank @Size(min = 3, max = 100) String title,
            @NotBlank @Size(min = 3, max = 200) String content, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }

    // Default constructor
    public BlogDTO() {}

    // Override toString method for easy representation
    @Override
    public String toString() {
        return "BlogDTO [id=" + id + ", title=" + title + ", content=" + content + ", createdAt=" + createdAt + "]";
    }
}

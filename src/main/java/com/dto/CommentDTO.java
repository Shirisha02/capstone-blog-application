package com.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Object (DTO) for Comment.
 * 
 * This class is used to transfer comment data between layers (e.g., service and controller).
 * It includes validation annotations to ensure that the comment is not blank and the blog ID is not null.
 */
public class CommentDTO {

    private Long id;

    @NotBlank(message = "Comment cannot be empty")
    private String comment;  // The content of the comment

    @NotNull(message = "Blog ID is required")
    private Long blogId;  // The ID of the blog associated with the comment

    private LocalDateTime createdAt;  // The timestamp of when the comment was created

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    // Override toString method for easy representation
    @Override
    public String toString() {
        return "CommentDTO [id=" + id + ", comment=" + comment + ", blogId=" + blogId + ", createdAt=" + createdAt + "]";
    }

    // Constructor with parameters
    public CommentDTO(Long id, @NotBlank(message = "Comment cannot be empty") String comment,
            @NotNull(message = "Blog ID is required") Long blogId, LocalDateTime createdAt) {
        this.id = id;
        this.comment = comment;
        this.blogId = blogId;
        this.createdAt = createdAt;
    }

    // Default constructor
    public CommentDTO() {
    }
}

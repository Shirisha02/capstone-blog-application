package com.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
public class CommentDTO {

    private Long id;

    @NotBlank(message = "Comment cannot be empty")  // Adjusted validation message
    private String comment;  // Changed from commentText to comment

    @NotNull(message = "Blog ID is required")
    private Long blogId;

    private LocalDateTime createdAt;

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

	@Override
	public String toString() {
		return "CommentDTO [id=" + id + ", comment=" + comment + ", blogId=" + blogId + ", createdAt=" + createdAt
				+ "]";
	}

	public CommentDTO(Long id, @NotBlank(message = "Comment cannot be empty") String comment,
			@NotNull(message = "Blog ID is required") Long blogId, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.comment = comment;
		this.blogId = blogId;
		this.createdAt = createdAt;
	}

	public CommentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}
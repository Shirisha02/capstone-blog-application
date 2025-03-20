package com.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Entity class representing a comment on a blog.
 * 
 * This class maps to the `comments` table in the database and contains fields for storing 
 * the comment's text, creation timestamp, and the associated blog it belongs to.
 */
@Entity
@Table(name = "comments")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String comment;

    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "blog_id", nullable = false)
    private BlogEntity blog;

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public BlogEntity getBlog() {
        return blog;
    }

    public void setBlog(BlogEntity blog) {
        this.blog = blog;
    }

    // Constructors
    public CommentEntity(Long id, String comment, LocalDateTime createdAt, BlogEntity blog) {
        super();
        this.id = id;
        this.comment = comment;
        this.createdAt = createdAt;
        this.blog = blog;
    }

    public CommentEntity() {
        super();
    }

    // Override toString for representation
    @Override
    public String toString() {
        return "CommentEntity [id=" + id + ", comment=" + comment + ", createdAt=" + createdAt + ", blog=" + blog + "]";
    }
}

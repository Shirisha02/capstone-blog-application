package com.controller;

import java.util.List;

import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.CommentDTO;
import com.service.CommentService;

import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * REST controller for handling comment-related operations.
 * 
 * This class provides endpoints to add, get, and delete comments. It uses the `CommentService` 
 * for business logic and interacts with the `CommentDTO` to transfer data.
 */
@RestController
@RequestMapping("/api/comments")
@Order(2)
public class CommentController {

    private final CommentService commentService;

    /**
     * Constructor-based dependency injection for the `CommentService`.
     * 
     * @param commentService The service layer responsible for comment-related logic.
     */
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    /**
     * Endpoint to add a new comment.
     * 
     * @param commentDTO The comment data to be added.
     * @return A ResponseEntity containing the created `CommentDTO` with status `201 Created`.
     */
    @Tag(name="Add Comment")
    @PostMapping
    public ResponseEntity<CommentDTO> addComment(@RequestBody CommentDTO commentDTO) {
        return ResponseEntity.status(201).body(commentService.addComment(commentDTO));
    }

    /**
     * Endpoint to retrieve all comments for a specific blog based on the blog ID.
     * 
     * @param blogId The ID of the blog for which comments are to be retrieved.
     * @return A ResponseEntity containing a list of `CommentDTO` objects.
     */
    @Tag(name="Get Comments By Blog Id")
    @GetMapping("/blog/{blogId}")
    public ResponseEntity<List<CommentDTO>> getCommentsByBlogId(@PathVariable Long blogId) {
        return ResponseEntity.ok(commentService.getCommentsByBlogId(blogId));
    }

    /**
     * Endpoint to delete a comment by its ID.
     * 
     * @param id The ID of the comment to be deleted.
     * @return A ResponseEntity containing a success message.
     */
    @Tag(name="Delete Comment")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.ok("Comment deleted successfully");
    }
}

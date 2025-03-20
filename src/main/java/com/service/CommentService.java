package com.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.dto.CommentDTO;

public interface CommentService {
    
    /**
     * Adds a new comment to a blog.
     * 
     * @param commentDTO The comment data transfer object containing comment details.
     * @return The added comment as a CommentDTO.
     */
    CommentDTO addComment(CommentDTO commentDTO);

    /**
     * Retrieves all comments for a specific blog by its ID.
     * 
     * @param blogId The ID of the blog for which comments are to be fetched.
     * @return A list of CommentDTOs representing the comments for the specified blog.
     */
    List<CommentDTO> getCommentsByBlogId(Long blogId);

    /**
     * Deletes a comment by its ID.
     * 
     * @param id The ID of the comment to be deleted.
     * @return A ResponseEntity indicating whether the deletion was successful.
     */
    ResponseEntity<String> deleteComment(Long id);
}

package com.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dto.CommentDTO;
import com.exception.BlogNotFoundException;
import com.exception.CommentNotFoundException;
import com.exception.CustomException;
import com.exception.InvalidBlogIdException;
import com.model.BlogEntity;
import com.model.CommentEntity;
import com.repository.BlogRepository;
import com.repository.CommentRepository;
import com.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final BlogRepository blogRepository;

    // Constructor for dependency injection of repositories.
    public CommentServiceImpl(CommentRepository commentRepository, BlogRepository blogRepository) {
        this.commentRepository = commentRepository;
        this.blogRepository = blogRepository;
    }

    /*
     * Adds a new comment for a blog.
     * 
     * Throws BlogNotFoundException if the blog with the specified ID does not exist.
     * 
     * @param commentDTO The comment data to be added to the blog.
     * @return The created comment as a CommentDTO.
     */
    @Override
    public CommentDTO addComment(CommentDTO commentDTO) {
        BlogEntity blog = blogRepository.findById(commentDTO.getBlogId())
                .orElseThrow(() -> new BlogNotFoundException("Blog not found"));

        CommentEntity comment = new CommentEntity();
        comment.setComment(commentDTO.getComment());
        comment.setBlog(blog);

        CommentEntity savedComment = commentRepository.save(comment);
        return new CommentDTO(savedComment.getId(), savedComment.getComment(), blog.getId(), savedComment.getCreatedAt());
    }

    /*
     * Retrieves all comments for a specific blog by its ID.
     * 
     * Throws InvalidBlogIdException if the blog ID is negative.
     * Throws CustomException if the blog does not exist in the database.
     * Throws CommentNotFoundException if no comments are found for the blog.
     * 
     * @param blogId The ID of the blog to retrieve comments for.
     * @return A list of CommentDTOs for the specified blog.
     */
    @Override
    public List<CommentDTO> getCommentsByBlogId(Long blogId) {
        // Check if the blogId is valid (not negative)
        if (blogId < 0) {
            throw new InvalidBlogIdException("Negative IDs are not allowed.");
        }

        // Check if the blog exists
        if (!blogRepository.existsById(blogId)) {
            throw new CustomException("Blog not found with ID: " + blogId);
        }

        // Retrieve comments for the blog
        List<CommentEntity> comments = commentRepository.findByBlogId(blogId);

        // If no comments are found, throw an exception
        if (comments.isEmpty()) {
            throw new CommentNotFoundException("No comments found for blog with ID: " + blogId);
        }

        // Convert the CommentEntity objects to CommentDTO and return
        return comments.stream()
                .map(comment -> new CommentDTO(comment.getId(), comment.getComment(), comment.getBlog().getId(), comment.getCreatedAt()))
                .collect(Collectors.toList());
    }

    /*
     * Deletes a comment by its ID.
     * 
     * Throws CustomException if the ID is negative or zero.
     * Throws CommentNotFoundException if the comment is not found by the specified ID.
     * 
     * @param id The ID of the comment to be deleted.
     * @return ResponseEntity containing a success message.
     */
    @Override
    public ResponseEntity<String> deleteComment(Long id) {
        // Check if the ID is valid (positive)
        if (id <= 0) {
            throw new CustomException("Invalid input: ID cannot be negative or zero.");
        }

        // Fetch the comment to be deleted
        CommentEntity comment = commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException("Comment not found"));

        // Delete the comment from the repository
        commentRepository.delete(comment);

        // Return success message
        return ResponseEntity.ok("Comment deleted successfully");
    }
}

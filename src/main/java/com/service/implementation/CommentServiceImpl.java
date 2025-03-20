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

    public CommentServiceImpl(CommentRepository commentRepository, BlogRepository blogRepository) {
        this.commentRepository = commentRepository;
        this.blogRepository = blogRepository;
    }

    @Override
    public CommentDTO addComment(CommentDTO commentDTO) {
        BlogEntity blog = blogRepository.findById(commentDTO.getBlogId())
                .orElseThrow(() -> new BlogNotFoundException("Blog not found"));

        CommentEntity comment = new CommentEntity();
        comment.setComment(commentDTO.getComment());  // Set the comment text
        comment.setBlog(blog);

        CommentEntity savedComment = commentRepository.save(comment);
        return new CommentDTO(savedComment.getId(), savedComment.getComment(), blog.getId(), savedComment.getCreatedAt());
    }

    @Override
    public List<CommentDTO> getCommentsByBlogId(Long blogId) {
    	if (blogId < 0) {
    	    throw new InvalidBlogIdException("Negative IDs are not allowed.");
    	}
    	if (!blogRepository.existsById(blogId)) {
            throw new CustomException("Blog not found with ID: " + blogId);
        }

        List<CommentEntity> comments = commentRepository.findByBlogId(blogId);
        
        if (comments.isEmpty()) {
            throw new CommentNotFoundException("No comments found for blog with ID: " + blogId);
        }
        return comments.stream()
                .map(comment -> new CommentDTO(comment.getId(), comment.getComment(), comment.getBlog().getId(), comment.getCreatedAt()))
                .collect(Collectors.toList());
    }

//    @Override
//    public void deleteComment(Long id) {
//        commentRepository.deleteById(id);
//    }
    
//    @Override
//    public ResponseEntity<String> deleteComment(Long id) {
//    	CommentEntity comment = commentRepository.findById(id)
//                .orElseThrow(() -> new CommentNotFoundException("Comment not found"));
//        
//        commentRepository.delete(comment);
//
//        return ResponseEntity.ok("Comment deleted successfully");// Return success message
//    }
    
    @Override
    public ResponseEntity<String> deleteComment(Long id) {
        // Check if the id is negative
        if (id <= 0) {
            throw new CustomException("Invalid input: ID cannot be negative or zero.");
        }

        // Fetch the comment to delete
        CommentEntity comment = commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException("Comment not found"));

        // Delete the comment
        commentRepository.delete(comment);

        // Return success message
        return ResponseEntity.ok("Comment deleted successfully");
    }
}
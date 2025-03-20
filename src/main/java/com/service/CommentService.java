package com.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.dto.CommentDTO;

public interface CommentService {
    CommentDTO addComment(CommentDTO commentDTO);
    List<CommentDTO> getCommentsByBlogId(Long blogId);
    ResponseEntity<String> deleteComment(Long id);
}

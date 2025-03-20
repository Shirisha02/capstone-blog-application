package com.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.dto.BlogDTO;

public interface BlogService {
    BlogDTO createBlog(BlogDTO blogDTO);
    BlogDTO getBlogById(Long id);
    BlogDTO updateBlog(Long id, BlogDTO blogDTO);
    ResponseEntity<String> deleteBlog(Long id); 
    List<BlogDTO> getAllBlogs();
}


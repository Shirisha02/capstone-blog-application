package com.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dto.BlogDTO;
import com.exception.BlogNotFoundException;
import com.model.BlogEntity;
import com.repository.BlogRepository;
import com.service.BlogService;

@Service
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;

    public BlogServiceImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public BlogDTO createBlog(BlogDTO blogDTO) {
        BlogEntity blog = new BlogEntity();
        blog.setTitle(blogDTO.getTitle());
        blog.setContent(blogDTO.getContent());
        BlogEntity savedBlog = blogRepository.save(blog);
        return new BlogDTO(savedBlog.getId(), savedBlog.getTitle(), savedBlog.getContent(), savedBlog.getCreatedAt());
    }

    @Override
    public BlogDTO getBlogById(Long id) {
    	BlogEntity blog = blogRepository.findById(id)
                .orElseThrow(() -> new BlogNotFoundException("Blog not found"));
        return new BlogDTO(blog.getId(), blog.getTitle(), blog.getContent(), blog.getCreatedAt());
    }

    @Override
    public BlogDTO updateBlog(Long id, BlogDTO blogDTO) {
    	BlogEntity blog = blogRepository.findById(id)
                .orElseThrow(() -> new BlogNotFoundException("Blog not found"));
        blog.setTitle(blogDTO.getTitle());
        blog.setContent(blogDTO.getContent());
        BlogEntity updatedBlog = blogRepository.save(blog);
        return new BlogDTO(updatedBlog.getId(), updatedBlog.getTitle(), updatedBlog.getContent(), updatedBlog.getCreatedAt());
    }

    @Override
    public ResponseEntity<String> deleteBlog(Long id) {
    	BlogEntity blog = blogRepository.findById(id)
                .orElseThrow(() -> new BlogNotFoundException("Blog not found"));
        
        blogRepository.delete(blog);

        return ResponseEntity.ok("Blog and related comments deleted successfully");// Return success message
    }

    @Override
    public List<BlogDTO> getAllBlogs() {
        return blogRepository.findAll().stream()
                .map(blog -> new BlogDTO(blog.getId(), blog.getTitle(), blog.getContent(), blog.getCreatedAt()))
                .collect(Collectors.toList());
    }
}
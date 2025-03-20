package com.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.dto.BlogDTO;

public interface BlogService {

    /**
     * Creates a new blog with the provided details.
     * 
     * @param blogDTO The blog data transfer object containing the blog details.
     * @return The created blog as a BlogDTO.
     */
    BlogDTO createBlog(BlogDTO blogDTO);

    /**
     * Retrieves a blog by its ID.
     * 
     * @param id The ID of the blog to retrieve.
     * @return The blog as a BlogDTO.
     */
    BlogDTO getBlogById(Long id);

    /**
     * Updates an existing blog with the provided details.
     * 
     * @param id The ID of the blog to update.
     * @param blogDTO The new blog details.
     * @return The updated blog as a BlogDTO.
     */
    BlogDTO updateBlog(Long id, BlogDTO blogDTO);

    /**
     * Deletes a blog by its ID.
     * 
     * @param id The ID of the blog to delete.
     * @return A response indicating the deletion status.
     */
    ResponseEntity<String> deleteBlog(Long id);

    /**
     * Retrieves all blogs.
     * 
     * @return A list of all blogs as BlogDTOs.
     */
    List<BlogDTO> getAllBlogs();
}

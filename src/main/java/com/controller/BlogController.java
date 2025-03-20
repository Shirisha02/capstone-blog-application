package com.controller;

import java.util.List;

import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.BlogDTO;
import com.service.BlogService;

import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * REST controller for handling blog-related operations.
 * 
 * This class provides endpoints to create, get, update, and delete blogs.
 * It uses the `BlogService` for business logic and interacts with the `BlogDTO` to transfer data.
 */
@RestController
@RequestMapping("/api/blogs")
@Order(1)
public class BlogController {

    private final BlogService blogService;

    /**
     * Constructor-based dependency injection for the `BlogService`.
     * 
     * @param blogService The service layer responsible for blog-related logic.
     */
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    /**
     * Endpoint to create a new blog.
     * 
     * @param blogDTO The blog data to be added.
     * @return A ResponseEntity containing the created `BlogDTO` with status `201 Created`.
     */
    @Tag(name="Add Blog")
    @PostMapping
    public ResponseEntity<BlogDTO> createBlog(@RequestBody BlogDTO blogDTO) {
        return ResponseEntity.status(201).body(blogService.createBlog(blogDTO));
    }

    /**
     * Endpoint to retrieve a blog by its ID.
     * 
     * @param id The ID of the blog to be retrieved.
     * @return A ResponseEntity containing the `BlogDTO` with the retrieved blog data.
     */
    @Tag(name="Get Blogs By Id")
    @GetMapping("/{id}")
    public ResponseEntity<BlogDTO> getBlogById(@PathVariable Long id) {
        return ResponseEntity.ok(blogService.getBlogById(id));
    }

    /**
     * Endpoint to update a blog's details by its ID.
     * 
     * @param id The ID of the blog to be updated.
     * @param blogDTO The new data for the blog.
     * @return A ResponseEntity containing the updated `BlogDTO`.
     */
    @Tag(name="Update Blog")
    @PutMapping("/{id}")
    public ResponseEntity<BlogDTO> updateBlog(@PathVariable Long id, @RequestBody BlogDTO blogDTO) {
        return ResponseEntity.ok(blogService.updateBlog(id, blogDTO));
    }

    /**
     * Endpoint to retrieve all blogs.
     * 
     * @return A ResponseEntity containing a list of all `BlogDTO` objects.
     */
    @Tag(name="Get All Blogs")
    @GetMapping
    public ResponseEntity<List<BlogDTO>> getAllBlogs() {
        return ResponseEntity.ok(blogService.getAllBlogs());
    }

    /**
     * Endpoint to delete a blog by its ID.
     * 
     * @param id The ID of the blog to be deleted.
     * @return A ResponseEntity with a success message after deletion.
     */
    @Tag(name="Delete Blogs")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBlog(@PathVariable Long id) {
        return blogService.deleteBlog(id);
    }
}

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

    // Constructor-based dependency injection for BlogRepository
    public BlogServiceImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    /**
     * Creates a new blog by accepting a BlogDTO object, converting it to a BlogEntity,
     * saving it to the database, and then returning the saved blog as a BlogDTO.
     * 
     * @param blogDTO The BlogDTO containing the details of the blog to be created.
     * @return The created BlogDTO with the saved blog details.
     * @throws BlogNotFoundException If there is an issue during saving the blog.
     */
    @Override
    public BlogDTO createBlog(BlogDTO blogDTO) {
        BlogEntity blog = new BlogEntity();  // Create a new BlogEntity object
        blog.setTitle(blogDTO.getTitle());   // Set the title of the blog
        blog.setContent(blogDTO.getContent()); // Set the content of the blog
        
        // Save the blog entity to the database
        BlogEntity savedBlog = blogRepository.save(blog);
        
        // Return the saved blog as BlogDTO
        return new BlogDTO(savedBlog.getId(), savedBlog.getTitle(), savedBlog.getContent(), savedBlog.getCreatedAt());
    }

    /**
     * Retrieves a blog by its ID. If the blog is not found, a BlogNotFoundException is thrown.
     * 
     * @param id The ID of the blog to be retrieved.
     * @return A BlogDTO containing the details of the blog.
     * @throws BlogNotFoundException If the blog with the given ID is not found.
     */
    @Override
    public BlogDTO getBlogById(Long id) {
        // Fetch the blog by ID, throwing an exception if not found
        BlogEntity blog = blogRepository.findById(id)
                .orElseThrow(() -> new BlogNotFoundException("Blog not found"));
        
        // Return the blog details as a BlogDTO
        return new BlogDTO(blog.getId(), blog.getTitle(), blog.getContent(), blog.getCreatedAt());
    }

    /**
     * Updates an existing blog by its ID with the details provided in the BlogDTO.
     * Throws BlogNotFoundException if the blog does not exist.
     * 
     * @param id The ID of the blog to be updated.
     * @param blogDTO The new details to update the blog.
     * @return A BlogDTO with the updated blog details.
     * @throws BlogNotFoundException If the blog with the given ID is not found.
     */
    @Override
    public BlogDTO updateBlog(Long id, BlogDTO blogDTO) {
        // Fetch the blog by ID, throwing an exception if not found
        BlogEntity blog = blogRepository.findById(id)
                .orElseThrow(() -> new BlogNotFoundException("Blog not found"));
        
        // Update the blog's title and content
        blog.setTitle(blogDTO.getTitle());
        blog.setContent(blogDTO.getContent());
        
        // Save the updated blog to the database
        BlogEntity updatedBlog = blogRepository.save(blog);
        
        // Return the updated blog as BlogDTO
        return new BlogDTO(updatedBlog.getId(), updatedBlog.getTitle(), updatedBlog.getContent(), updatedBlog.getCreatedAt());
    }

    /**
     * Deletes a blog by its ID and returns a success message. If the blog is not found, a BlogNotFoundException is thrown.
     * 
     * @param id The ID of the blog to be deleted.
     * @return A success message indicating that the blog and related comments have been deleted.
     * @throws BlogNotFoundException If the blog with the given ID is not found.
     */
    @Override
    public ResponseEntity<String> deleteBlog(Long id) {
        // Fetch the blog by ID, throwing an exception if not found
        BlogEntity blog = blogRepository.findById(id)
                .orElseThrow(() -> new BlogNotFoundException("Blog not found"));
        
        // Delete the blog from the database
        blogRepository.delete(blog);

        // Return success message
        return ResponseEntity.ok("Blog and related comments deleted successfully");
    }

    /**
     * Retrieves all the blogs from the database and returns them as a list of BlogDTOs.
     * 
     * @return A list of BlogDTOs containing details of all blogs in the database.
     */
    @Override
    public List<BlogDTO> getAllBlogs() {
        // Fetch all blogs and map them to BlogDTOs
        return blogRepository.findAll().stream()
                .map(blog -> new BlogDTO(blog.getId(), blog.getTitle(), blog.getContent(), blog.getCreatedAt()))
                .collect(Collectors.toList());
    }
}

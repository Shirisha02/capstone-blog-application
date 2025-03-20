package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.CommentEntity;

/**
 * Repository interface for accessing the `CommentEntity` in the database.
 * 
 * This interface extends `JpaRepository` to provide CRUD operations for `CommentEntity` objects.
 * The `@Repository` annotation marks this interface as a Spring Data repository, 
 * making it eligible for component scanning and enabling Spring Data JPA functionalities.
 */
@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    /**
     * Retrieves a list of comments for a specific blog by its ID.
     * 
     * @param blogId The ID of the blog for which comments are to be retrieved.
     * @return A list of CommentEntity objects associated with the provided blog ID.
     */
    List<CommentEntity> findByBlogId(Long blogId);
}

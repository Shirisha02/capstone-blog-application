package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.BlogEntity;

/**
 * Repository interface for accessing `BlogEntity` in the database.
 * 
 * This interface extends `JpaRepository` to provide CRUD operations for `BlogEntity` objects.
 * The `@Repository` annotation marks this interface as a Spring Data repository, 
 * making it eligible for component scanning and enabling Spring Data JPA functionalities.
 */
@Repository
public interface BlogRepository extends JpaRepository<BlogEntity, Long> {
    // This interface inherits all CRUD and query capabilities from JpaRepository
}

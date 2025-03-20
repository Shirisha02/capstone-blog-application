package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.BlogEntity;

public interface BlogRepository extends JpaRepository<BlogEntity, Long> {}

package com.example.newsmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.newsmanager.domain.category.Category;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);
    boolean existsByName(String name);
}
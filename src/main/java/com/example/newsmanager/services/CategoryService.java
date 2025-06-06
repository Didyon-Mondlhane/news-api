package com.example.newsmanager.services;

import com.example.newsmanager.domain.category.*;
import com.example.newsmanager.repositories.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    // Métodos CRUD serão implementados aqui
    @Transactional
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        if (categoryRepository.existsByName(categoryDTO.name())) {
            throw new RuntimeException("Category name already exists");
        }

        Category newCategory = Category.builder()
                .name(categoryDTO.name())
                .description(categoryDTO.description())
                .build();

        Category savedCategory = categoryRepository.save(newCategory);
        return new CategoryDTO(savedCategory);
    }
}
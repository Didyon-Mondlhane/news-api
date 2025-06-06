package com.example.newsmanager.domain.category;

public record CategoryDTO(
    Long id,
    String name,
    String description
) {
    public CategoryDTO(Category category) {
        this(
            category.getId(),
            category.getName(),
            category.getDescription()
        );
    }
}
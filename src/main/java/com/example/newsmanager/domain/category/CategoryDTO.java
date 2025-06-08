package com.example.newsmanager.domain.category;

public record CategoryDTO(
    String name,
    String description
) {
    public CategoryDTO(Category category) {
        this(
            category.getName(),
            category.getDescription()
        );
    }
}
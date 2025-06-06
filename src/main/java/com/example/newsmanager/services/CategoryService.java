package com.example.newsmanager.services;

import com.example.newsmanager.domain.category.Category;
import com.example.newsmanager.domain.category.CategoryDTO;
import com.example.newsmanager.repositories.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<CategoryDTO> getAllCategories() {
        return repository.findAll().stream()
                .map(CategoryDTO::new)
                .collect(Collectors.toList());
    }

    public CategoryDTO getCategoryById(Long id) {
        Category category = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        return new CategoryDTO(category);
    }

    @Transactional
    public CategoryDTO createCategory(CategoryDTO dto) {
        if (repository.existsByName(dto.name())) {
            throw new RuntimeException("O nome da categoria já existe");
        }

        Category category = Category.builder()
                .name(dto.name())
                .description(dto.description())
                .build();

        Category savedCategory = repository.save(category);
        return new CategoryDTO(savedCategory);
    }

    @Transactional
    public CategoryDTO updateCategory(Long id, CategoryDTO dto) {
        Category category = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        category.setName(dto.name());
        category.setDescription(dto.description());

        Category updatedCategory = repository.save(category);
        return new CategoryDTO(updatedCategory);
    }

    @Transactional
    public void deleteCategory(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Categoria não encontrada");
        }
        repository.deleteById(id);
    }
}

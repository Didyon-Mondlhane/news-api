package com.example.newsmanager.services;

import com.example.newsmanager.domain.category.Category;
import com.example.newsmanager.domain.category.CategoryDTO;
import com.example.newsmanager.repositories.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<CategoryDTO> getAll() {
        return repository.findAll().stream()
                .map(CategoryDTO::new)
                .toList();
    }

    public CategoryDTO getById(Long id) {
        Category category = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return new CategoryDTO(category);
    }

    @Transactional
    public CategoryDTO create(CategoryDTO dto) {
        if (repository.existsByName(dto.name())) {
            throw new RuntimeException("Category name already exists");
        }

        Category category = new Category();
        category.setName(dto.name());
        category.setDescription(dto.description());

        Category savedCategory = repository.save(category);
        return new CategoryDTO(savedCategory);
    }

    @Transactional
    public CategoryDTO update(Long id, CategoryDTO dto) {
        Category category = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        if (!category.getName().equals(dto.name()) && repository.existsByName(dto.name())) {
            throw new RuntimeException("Category name already exists");
        }

        category.setName(dto.name());
        category.setDescription(dto.description());

        Category updatedCategory = repository.save(category);
        return new CategoryDTO(updatedCategory);
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Category not found");
        }
        repository.deleteById(id);
    }
}
package com.example.newsmanager.domain.news;

import jakarta.validation.constraints.NotBlank;

public record NewsRequestDTO(
    @NotBlank String title,
    String subtitle,
    @NotBlank String content,
    String imageUrl,
    @NotBlank String authorId,
    @NotBlank String category
) {
}
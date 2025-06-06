package com.example.newsmanager.domain.news;

import java.time.LocalDateTime;
import java.util.List;

public record NewsResponseDTO(
    String id,
    String title,
    String subtitle,
    String content,
    String imageUrl,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    String authorId,
    String category,
    List<NewsImageDTO> images
) {
    public NewsResponseDTO(News news, List<NewsImageDTO> images) {
        this(
            news.getId(),
            news.getTitle(),
            news.getSubtitle(),
            news.getContent(),
            news.getImageUrl(),
            news.getCreatedAt(),
            news.getUpdatedAt(),
            news.getAuthorId(),
            news.getCategory(),
            images
        );
    }
}
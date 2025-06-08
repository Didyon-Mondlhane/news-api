package com.example.newsmanager.domain.news;

public record NewsRequestDTO(
    String title,
    String subtitle,
    String content,
    String imageUrl,
    Long authorId,  
    String categoryName  
) {}
package com.example.newsmanager.domain.news;

import java.time.LocalDateTime;
import java.util.List;

import com.example.newsmanager.domain.comment.CommentDTO;

public record NewsResponseDTO(
    String id,
    String title,
    String subtitle,
    String content,
    String imageUrl,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    
    AuthorInfo author,
    
    CategoryInfo category,
    
    List<CommentDTO> comments,
    
    int reactionCount
) {
    public record AuthorInfo(
        String id,
        String username,
        String email
    ) {}
    
    public record CategoryInfo(
        String id,
        String name,
        String description
    ) {}
    
    public NewsResponseDTO(News news, List<CommentDTO> comments) {
        this(
            news.getId(),
            news.getTitle(),
            news.getSubtitle(),
            news.getContent(),
            news.getImageUrl(),
            news.getCreatedAt(),
            news.getUpdatedAt(),
            
            new AuthorInfo(
                news.getAuthor().getId().toString(),
                news.getAuthor().getUsername(),
                news.getAuthor().getEmail()
            ),
            
            new CategoryInfo(
                news.getCategory().getId().toString(),
                news.getCategory().getName(),
                news.getCategory().getDescription()
            ),
            
            comments,
            
            news.getReactions() != null ? news.getReactions().size() : 0
        );
    }
}
package com.example.newsmanager.domain.reaction;

import java.time.LocalDateTime;

public record ReactionDTO(
    String type,
    LocalDateTime createdAt,
    Long userId,
    String newsId
) {
    public ReactionDTO(Reaction reaction) {
        this(
            reaction.getType(),
            reaction.getCreatedAt(),
            reaction.getUser().getId(),
            reaction.getNews().getId() 
        );
    }
}
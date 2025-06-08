package com.example.newsmanager.domain.reaction;

import java.time.LocalDateTime;

public record ReactionDTO(
    Long id,
    String type,
    LocalDateTime createdAt,
    Long userId,
    String newsId  
) {
    public ReactionDTO(Reaction reaction) {
        this(
            reaction.getId(),
            reaction.getType(),
            reaction.getCreatedAt(),
            reaction.getUserId(),
            reaction.getNewsId()
        );
    }
}
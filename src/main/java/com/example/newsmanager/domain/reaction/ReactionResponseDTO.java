
package com.example.newsmanager.domain.reaction;

import java.time.LocalDateTime;

public record ReactionResponseDTO(
    Long id,
    ReactionType type,
    LocalDateTime createdAt,
    Long userId,
    Long newsId
) {}
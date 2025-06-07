// ReactionRequestDTO.java
package com.example.newsmanager.domain.reaction;

import jakarta.validation.constraints.NotNull;

public record ReactionRequestDTO(
    @NotNull Long id,
    @NotNull ReactionType type,
    @NotNull Long userId,
    @NotNull String newsId
) {
}

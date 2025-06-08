package com.example.newsmanager.domain.reaction;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateReactionDTO(
    @NotBlank(message = "Reaction type is required")
    String type,
    
    @NotNull(message = "User ID is required")
    Long userId,
    
    @NotNull(message = "News ID is required")
    Long newsId
) {}

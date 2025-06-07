package com.example.newsmanager.domain.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateCommentDTO(
    @NotBlank String content,
    @NotNull Long newsId,
    Long parentId
) {}

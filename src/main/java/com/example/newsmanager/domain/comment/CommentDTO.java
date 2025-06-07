package com.example.newsmanager.domain.comment;

import java.time.LocalDateTime;
import java.util.List;

public record CommentDTO(
    Long id,
    String content,
    Long userId,
    String username,
    Long newsId,
    Long parentId,
    LocalDateTime createdAt,
    List<CommentDTO> replies
) {}

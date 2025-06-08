package com.example.newsmanager.domain.comment;

import java.time.LocalDateTime;

public record CommentDTO(
    Long id,
    String content,
    LocalDateTime createdAt,
    String newsId,
    Long authorId
) {
    public CommentDTO(Comment comment) {
        this(
            comment.getId(),
            comment.getContent(),
            comment.getCreatedAt(),
            comment.getNewsId(),
            comment.getAuthorId()
        );
    }
}
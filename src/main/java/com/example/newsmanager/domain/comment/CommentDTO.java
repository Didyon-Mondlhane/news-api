package com.example.newsmanager.domain.comment;

import java.time.LocalDateTime;
import java.util.List;

public record CommentDTO(
    Long id,
    String content,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    Long newsId,
    Long authorId,
    Long parentId,
    List<CommentDTO> replies
) {
    public CommentDTO(Comment comment) {
        this(
            comment.getId(),
            comment.getContent(),
            comment.getCreatedAt(),
            comment.getUpdatedAt(),
            comment.getNews() != null ? comment.getNews().getId() : null,
            comment.getAuthor() != null ? comment.getAuthor().getId() : null,
            comment.getParent() != null ? comment.getParent().getId() : null,
            comment.getReplies() != null ? 
                comment.getReplies().stream().map(CommentDTO::new).toList() : 
                List.of()
        );
    }
}
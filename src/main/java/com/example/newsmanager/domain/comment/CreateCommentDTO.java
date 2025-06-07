package com.example.newsmanager.domain.comment;

public record CreateCommentDTO(
    String content,
    Long newsId,
    Long parentId
) {}

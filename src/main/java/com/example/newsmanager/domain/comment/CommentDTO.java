package com.example.newsmanager.domain.comment;

import java.time.LocalDateTime;

public record CommentDTO(
    Long id,  
    String content,
    LocalDateTime createdAt,
    NewsInfo news,  
    AuthorInfo author  
) {
    public record NewsInfo(
        String id,
        String title
    ) {}
    
    public record AuthorInfo(
        Long id,
        String username
    ) {}
    
    public CommentDTO(Comment comment) {
        this(
            comment.getId(),
            comment.getContent(),
            comment.getCreatedAt(),
            new NewsInfo(
                comment.getNews().getId(),
                comment.getNews().getTitle()
            ),
            new AuthorInfo(
                comment.getAuthor().getId(),
                comment.getAuthor().getUsername()
            )
        );
    }

}
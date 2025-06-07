package com.example.newsmanager.services;

import com.example.newsmanager.domain.comment.CommentDTO;
import com.example.newsmanager.domain.comment.CreateCommentDTO;

import java.util.List;

public interface CommentService {
    CommentDTO createComment(CreateCommentDTO dto, Long userId);
    List<CommentDTO> getCommentsByNewsId(Long newsId);
    List<CommentDTO> getReplies(Long commentId);
    void deleteComment(Long commentId, Long userId);
    long countCommentsByNewsId(Long newsId);
    List<CommentDTO> getLatestComments(Long newsId, int limit);
}

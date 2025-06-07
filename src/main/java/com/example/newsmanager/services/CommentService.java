package com.example.newsmanager.services;

import com.example.newsmanager.domain.comment.*;
import com.example.newsmanager.domain.news.News;
import com.example.newsmanager.domain.user.User;
import com.example.newsmanager.repositories.CommentRepository;
import com.example.newsmanager.repositories.NewsRepository;
import com.example.newsmanager.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final NewsRepository newsRepository;
    private final UserRepository userRepository;

    public CommentService(CommentRepository commentRepository, 
                        NewsRepository newsRepository,
                        UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.newsRepository = newsRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public CommentDTO createComment(CreateCommentDTO commentDTO, Long authorId) {
        News news = newsRepository.findById(commentDTO.newsId())
                .orElseThrow(() -> new RuntimeException("News not found"));
        
        User author = userRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        Comment parent = null;
        if (commentDTO.parentId() != null) {
            parent = commentRepository.findById(commentDTO.parentId())
                    .orElseThrow(() -> new RuntimeException("Parent comment not found"));
        }
        
        Comment newComment = new Comment();
        newComment.setContent(commentDTO.content());
        newComment.setNews(news);
        newComment.setAuthor(author);
        newComment.setParent(parent);
        
        Comment savedComment = commentRepository.save(newComment);
        return new CommentDTO(savedComment);
    }

    public List<CommentDTO> getCommentsByNewsId(Long newsId) {
        List<Comment> comments = commentRepository.findByNewsIdAndParentIsNull(newsId);
        return comments.stream()
                .map(CommentDTO::new)
                .collect(Collectors.toList());
    }

    public List<CommentDTO> getReplies(Long commentId) {
        List<Comment> replies = commentRepository.findByParentId(commentId);
        return replies.stream()
                .map(CommentDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteComment(Long commentId, Long userId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        
        if (!comment.getAuthor().getId().equals(userId)) {
            throw new RuntimeException("You can only delete your own comments");
        }
        
        commentRepository.delete(comment);
    }

    public long countCommentsByNewsId(Long newsId) {
        return commentRepository.countByNewsId(newsId);
    }

    public List<CommentDTO> getLatestComments(Long newsId, int limit) {
        List<Comment> comments = commentRepository.findLatestByNewsId(newsId, Pageable.ofSize(limit));
        return comments.stream()
                .map(CommentDTO::new)
                .collect(Collectors.toList());
    }
}
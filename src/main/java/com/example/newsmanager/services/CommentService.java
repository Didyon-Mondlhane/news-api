package com.example.newsmanager.services;

import com.example.newsmanager.domain.auth.User;
import com.example.newsmanager.domain.comment.Comment;
import com.example.newsmanager.domain.comment.CommentDTO;
import com.example.newsmanager.domain.news.News;
import com.example.newsmanager.repositories.CommentRepository;
import com.example.newsmanager.repositories.NewsRepository;
import com.example.newsmanager.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final NewsRepository newsRepository;
    private final UserRepository userRepository;

    public CommentService(CommentRepository repository, 
                        NewsRepository newsRepository,
                        UserRepository userRepository) {
        this.commentRepository = repository;
        this.newsRepository = newsRepository;
        this.userRepository = userRepository;
    }

    public List<CommentDTO> getByNewsId(String newsId) {
        return commentRepository.findByNewsId(newsId).stream()
                .map(CommentDTO::new)
                .toList();
    }

    @Transactional
    public CommentDTO create(CommentDTO dto) {
        News news = newsRepository.findById(dto.news().id())
                .orElseThrow(() -> new RuntimeException("News not found"));
        User author = userRepository.findById(dto.author().id())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Comment comment = new Comment();
        comment.setContent(dto.content());
        comment.setNews(news);
        comment.setAuthor(author);
        
        Comment savedComment = commentRepository.save(comment);
        return new CommentDTO(savedComment);
    }

    @Transactional
    public void delete(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        commentRepository.delete(comment);
    }
}
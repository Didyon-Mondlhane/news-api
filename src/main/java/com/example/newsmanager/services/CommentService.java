package com.example.newsmanager.services;

import com.example.newsmanager.domain.comment.Comment;
import com.example.newsmanager.domain.comment.CommentDTO;
import com.example.newsmanager.repositories.CommentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<CommentDTO> getByNewsId(String newsId) {
        return commentRepository.findByNewsId(newsId).stream()
                .map(CommentDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentDTO create(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setContent(commentDTO.content());
        comment.setNewsId(commentDTO.newsId());
        comment.setAuthorId(commentDTO.authorId());
        
        Comment savedComment = commentRepository.save(comment);
        return new CommentDTO(savedComment);
    }

    @Transactional
    public void delete(Long id) {
        commentRepository.deleteById(id);
    }
}
package com.example.newsmanager.controllers;

import com.example.newsmanager.domain.comment.CommentDTO;
import com.example.newsmanager.services.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/news/{newsId}")
    public ResponseEntity<List<CommentDTO>> getCommentsByNews(@PathVariable String newsId) {
        List<CommentDTO> comments = commentService.getByNewsId(newsId);
        return ResponseEntity.ok(comments);
    }

    @PostMapping
    public ResponseEntity<CommentDTO> createComment(@RequestBody @Valid CommentDTO commentDTO) {
        CommentDTO createdComment = commentService.create(commentDTO);
        return ResponseEntity.ok(createdComment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
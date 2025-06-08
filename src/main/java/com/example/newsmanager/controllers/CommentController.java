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
    public ResponseEntity<List<CommentDTO>> getByNewsId(@PathVariable String newsId) {
        return ResponseEntity.ok(commentService.getByNewsId(newsId));
    }

    @PostMapping
    public ResponseEntity<CommentDTO> create(@RequestBody @Valid CommentDTO commentDTO) {
        return ResponseEntity.ok(commentService.create(commentDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        commentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
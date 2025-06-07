package com.example.newsmanager.controllers;

import com.example.newsmanager.domain.comment.CommentDTO;
import com.example.newsmanager.domain.comment.CreateCommentDTO;
import com.example.newsmanager.services.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<CommentDTO> createComment(
            @RequestBody @Valid CreateCommentDTO commentDTO,
            @AuthenticationPrincipal UserDetails userDetails) {
        Long userId = Long.parseLong(userDetails.getUsername());
        CommentDTO createdComment = commentService.createComment(commentDTO, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
    }

    @GetMapping("/news/{newsId}")
    public ResponseEntity<List<CommentDTO>> getCommentsByNewsId(@PathVariable Long newsId) {
        List<CommentDTO> comments = commentService.getCommentsByNewsId(newsId);
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/{commentId}/replies")
    public ResponseEntity<List<CommentDTO>> getReplies(@PathVariable Long commentId) {
        List<CommentDTO> replies = commentService.getReplies(commentId);
        return ResponseEntity.ok(replies);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Long commentId,
            @AuthenticationPrincipal UserDetails userDetails) {
        Long userId = Long.parseLong(userDetails.getUsername());
        commentService.deleteComment(commentId, userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/news/{newsId}/count")
    public ResponseEntity<Long> countCommentsByNewsId(@PathVariable Long newsId) {
        long count = commentService.countCommentsByNewsId(newsId);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/news/{newsId}/latest")
    public ResponseEntity<List<CommentDTO>> getLatestComments(
            @PathVariable Long newsId,
            @RequestParam(defaultValue = "5") int limit) {
        List<CommentDTO> comments = commentService.getLatestComments(newsId, limit);
        return ResponseEntity.ok(comments);
    }
}

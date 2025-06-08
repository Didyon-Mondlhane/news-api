package com.example.newsmanager.controllers;

import com.example.newsmanager.domain.reaction.ReactionDTO;
import com.example.newsmanager.services.ReactionService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/reactions")
public class ReactionController {

    private final ReactionService reactionService;

    public ReactionController(ReactionService reactionService) {
        this.reactionService = reactionService;
    }

    @PostMapping
    public ResponseEntity<ReactionDTO> create(@RequestBody @Valid ReactionDTO reactionDTO) {
        return ResponseEntity.ok(reactionService.create(reactionDTO));
    }

    @GetMapping("/news/{newsId}")
    public ResponseEntity<List<ReactionDTO>> getByNewsId(@PathVariable String newsId) {
        return ResponseEntity.ok(reactionService.getByNewsId(newsId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        reactionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
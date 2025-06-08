package com.example.newsmanager.controllers;

import com.example.newsmanager.domain.reaction.ReactionDTO;
import com.example.newsmanager.services.ReactionService;
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
    public ResponseEntity<ReactionDTO> addReaction(@RequestBody @Valid ReactionDTO reactionDTO) {
        ReactionDTO createdReaction = reactionService.addReaction(reactionDTO);
        return ResponseEntity.ok(createdReaction);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeReaction(@PathVariable Long id) {
        reactionService.removeReaction(id);
        return ResponseEntity.noContent().build();
    }
}
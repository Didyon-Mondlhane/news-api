package com.example.newsmanager.controllers;

import com.example.newsmanager.domain.reaction.ReactionRequestDTO;
import com.example.newsmanager.domain.reaction.ReactionResponseDTO;
import com.example.newsmanager.services.ReactionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reactions")
public class ReactionController {

    private final ReactionService service;

    public ReactionController(ReactionService service) {
        this.service = service;
    }

    @GetMapping
    public List<ReactionResponseDTO> getAllReactions() {
        return service.getAllReactions();
    }

    @GetMapping("/{id}")
    public ReactionResponseDTO getReactionById(@PathVariable Long id) {
        return service.getReactionById(id);
    }

    @PostMapping("/add-reaction")
    public ResponseEntity<ReactionResponseDTO> createReaction(@Valid @RequestBody ReactionRequestDTO dto) {
        ReactionResponseDTO created = service.createReaction(dto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/change-reaction")
    public ResponseEntity<ReactionResponseDTO> updateReaction(@Valid @RequestBody ReactionRequestDTO dto) {
        ReactionResponseDTO updated = service.updateReaction(dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/remove-reaction/{id}")
    public ResponseEntity<Void> deleteReaction(@PathVariable Long id) {
        service.deleteReaction(id);
        return ResponseEntity.noContent().build();
    }
}
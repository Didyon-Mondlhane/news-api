package com.example.newsmanager.services;

import com.example.newsmanager.domain.reaction.Reaction;
import com.example.newsmanager.domain.reaction.ReactionRequestDTO;
import com.example.newsmanager.domain.reaction.ReactionResponseDTO;
import com.example.newsmanager.repositories.ReactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReactionService {
    private final ReactionRepository repository;

    public ReactionService(ReactionRepository repository) {
        this.repository = repository;
    }

    public List<ReactionResponseDTO> getAllReactions() {
        return repository.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public List<ReactionResponseDTO> getReactionsByNewsId(String newsId) {
        return repository.findByNewsId(newsId).stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public List<ReactionResponseDTO> getReactionsByUserId(Long userId) {
        return repository.findByUserId(userId).stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public ReactionResponseDTO getReactionById(Long id) {
        Reaction reaction = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reação não encontrada"));
        return toResponseDTO(reaction);
    }

    @Transactional
    public ReactionResponseDTO createReaction(ReactionRequestDTO dto) {
        if (repository.findByUserIdAndNewsId(dto.userId(), dto.newsId()).isPresent()) {
            throw new RuntimeException("Usuário já reagiu a esta notícia");
        }

        Reaction reaction = new Reaction();
        reaction.setType(dto.type());
        reaction.setUserId(dto.userId());
        reaction.setNewsId(dto.newsId());

        Reaction saved = repository.save(reaction);
        return toResponseDTO(saved);
    }

    
    @Transactional
    public ReactionResponseDTO updateReaction(ReactionRequestDTO dto) {
    Reaction reaction = repository.findById(dto.id())
            .orElseThrow(() -> new RuntimeException("Reação não encontrada para atualizar"));

    reaction.setType(dto.type());
    Reaction updated = repository.save(reaction);
    return toResponseDTO(updated);
}

    @Transactional
    public void deleteReaction(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Reação não encontrada");
        }
        repository.deleteById(id);
    }

    private ReactionResponseDTO toResponseDTO(Reaction reaction) {
        return new ReactionResponseDTO(
                reaction.getId(),
                reaction.getType(),
                reaction.getCreatedAt(),
                reaction.getUserId(),
                reaction.getNewsId()
        );
    }
}
package com.example.newsmanager.services;

import com.example.newsmanager.domain.reaction.Reaction;
import com.example.newsmanager.domain.reaction.ReactionDTO;
import com.example.newsmanager.repositories.ReactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReactionService {

    private final ReactionRepository reactionRepository;

    public ReactionService(ReactionRepository reactionRepository) {
        this.reactionRepository = reactionRepository;
    }

    @Transactional
    public ReactionDTO addReaction(ReactionDTO reactionDTO) {
        if (reactionRepository.existsByUserIdAndNewsId(reactionDTO.userId(), reactionDTO.newsId())) {
            throw new IllegalArgumentException("Utilizador já reagiu a esta notícia");
        }

        Reaction reaction = new Reaction();
        reaction.setType(reactionDTO.type());
        reaction.setNewsId(reactionDTO.newsId());
        reaction.setUserId(reactionDTO.userId());
        
        Reaction savedReaction = reactionRepository.save(reaction);
        return new ReactionDTO(savedReaction);
    }

    @Transactional
    public void removeReaction(Long id) {
        if (!reactionRepository.existsById(id)) {
            throw new IllegalArgumentException("Reacção não encontrada");
        }
        reactionRepository.deleteById(id);
    }

    public List<ReactionDTO> getReactionsByNews(String newsId) {
        return reactionRepository.findByNewsId(newsId).stream()
                .map(ReactionDTO::new)
                .toList();
    }
}
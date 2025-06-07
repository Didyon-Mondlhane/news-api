package com.example.newsmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.newsmanager.domain.reaction.Reaction;

import java.util.List;
import java.util.Optional;

public interface ReactionRepository extends JpaRepository<Reaction, Long> {
    List<Reaction> findByNewsId(String newsId);
    List<Reaction> findByUserId(Long userId);
    Optional<Reaction> findByUserIdAndNewsId(Long userId, String newsId);
}
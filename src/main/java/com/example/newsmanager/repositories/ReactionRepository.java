package com.example.newsmanager.repositories;

import com.example.newsmanager.domain.reaction.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReactionRepository extends JpaRepository<Reaction, Long> {
    List<Reaction> findByNewsId(String newsId);
    List<Reaction> findByUserId(Long userId);
    boolean existsByUserIdAndNewsId(Long userId, String newsId);

}
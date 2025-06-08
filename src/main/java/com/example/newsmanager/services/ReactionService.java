package com.example.newsmanager.services;

import com.example.newsmanager.domain.reaction.Reaction;
import com.example.newsmanager.domain.reaction.ReactionDTO;
import com.example.newsmanager.repositories.ReactionRepository;
import com.example.newsmanager.repositories.UserRepository;
import com.example.newsmanager.repositories.NewsRepository;
import com.example.newsmanager.domain.auth.User;
import com.example.newsmanager.domain.news.News;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReactionService {
    private final ReactionRepository repository;
    private final UserRepository userRepository;
    private final NewsRepository newsRepository;

    public ReactionService(ReactionRepository repository, UserRepository userRepository, NewsRepository newsRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.newsRepository = newsRepository;
    }

    @Transactional
    public ReactionDTO create(ReactionDTO dto) {
        if (repository.existsByUserIdAndNewsId(dto.userId(), dto.newsId())) {
            throw new RuntimeException("User already reacted to this news");
        }

        User user = userRepository.findById(dto.userId())
            .orElseThrow(() -> new RuntimeException("User not found"));
        News news = newsRepository.findById(dto.newsId())
            .orElseThrow(() -> new RuntimeException("News not found"));

        Reaction reaction = new Reaction();
        reaction.setType(dto.type());
        reaction.setUser(user);
        reaction.setNews(news);

        Reaction savedReaction = repository.save(reaction);
        return new ReactionDTO(savedReaction);
    }

    public List<ReactionDTO> getByNewsId(String newsId) {
        return repository.findByNewsId(newsId).stream()
                .map(ReactionDTO::new)
                .toList();
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Reaction not found");
        }
        repository.deleteById(id);
    }
}

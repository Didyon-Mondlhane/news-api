package com.example.newsmanager.services;

import com.example.newsmanager.domain.auth.User;
import com.example.newsmanager.domain.category.Category;
import com.example.newsmanager.repositories.CategoryRepository;
import com.example.newsmanager.domain.comment.CommentDTO;
import com.example.newsmanager.domain.news.News;
import com.example.newsmanager.repositories.NewsRepository;
import com.example.newsmanager.repositories.UserRepository;
import com.example.newsmanager.domain.news.NewsRequestDTO;
import com.example.newsmanager.domain.news.NewsResponseDTO;
import com.example.newsmanager.domain.reaction.ReactionDTO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsService {
    private final NewsRepository newsRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public NewsService(
        NewsRepository newsRepository,
        CategoryRepository categoryRepository,
        UserRepository userRepository
    ) {
        this.newsRepository = newsRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public List<NewsResponseDTO> getAll() {
        return newsRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public NewsResponseDTO getById(String id) {
        News news = newsRepository.findByIdWithCommentsAndReactions(id)
                .orElseThrow(() -> new RuntimeException("News not found"));
        return convertToDTO(news);
    }

    @Transactional
    public NewsResponseDTO create(NewsRequestDTO dto) {
        User author = userRepository.findById(dto.authorId())
                .orElseThrow(() -> new RuntimeException("Author not found"));
        Category category = categoryRepository.findByName(dto.categoryName())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        News news = new News();
        news.setTitle(dto.title());
        news.setSubtitle(dto.subtitle());
        news.setContent(dto.content());
        news.setImageUrl(dto.imageUrl());
        news.setAuthor(author);
        news.setCategory(category);

        News savedNews = newsRepository.save(news);
        return convertToDTO(savedNews);
    }

    @Transactional
    public NewsResponseDTO update(String id, NewsRequestDTO dto) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("News not found"));
        Category category = categoryRepository.findByName(dto.categoryName())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        news.setTitle(dto.title());
        news.setSubtitle(dto.subtitle());
        news.setContent(dto.content());
        news.setImageUrl(dto.imageUrl());
        news.setCategory(category);

        News updatedNews = newsRepository.save(news);
        return convertToDTO(updatedNews);
    }

    @Transactional
    public void delete(String id) {
        News news = newsRepository.findByIdWithCommentsAndReactions(id)
                .orElseThrow(() -> new RuntimeException("News not found"));
        newsRepository.delete(news);
    }

    private NewsResponseDTO convertToDTO(News news) {
        List<CommentDTO> commentDTOs = news.getComments().stream()
                .map(CommentDTO::new)
                .collect(Collectors.toList());

        List<ReactionDTO> reactionDTOs = news.getReactions().stream()
                .map(ReactionDTO::new)
                .collect(Collectors.toList());

        return new NewsResponseDTO(
            news.getId(),
            news.getTitle(),
            news.getSubtitle(),
            news.getContent(),
            news.getImageUrl(),
            news.getCreatedAt(),
            news.getUpdatedAt(),
            new NewsResponseDTO.AuthorInfo(
                news.getAuthor().getId().toString(),
                news.getAuthor().getUsername(),
                news.getAuthor().getEmail()
            ),
            new NewsResponseDTO.CategoryInfo(
                news.getCategory().getId().toString(),
                news.getCategory().getName(),
                news.getCategory().getDescription()
            ),
            commentDTOs,
            reactionDTOs.size()
        );
    }
}
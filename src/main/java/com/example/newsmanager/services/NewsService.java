package com.example.newsmanager.services;

import com.example.newsmanager.domain.news.News;
import com.example.newsmanager.domain.news.NewsRequestDTO;
import com.example.newsmanager.domain.news.NewsResponseDTO;
import com.example.newsmanager.repositories.NewsRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsService {
    private final NewsRepository repository;

    public NewsService(NewsRepository repository) {
        this.repository = repository;
    }


    public List<NewsResponseDTO> getAll() {
        return repository.findAll().stream()
                .map(news -> new NewsResponseDTO(news, List.of()))
                .collect(Collectors.toList());
    }

    public NewsResponseDTO getById(String id) {
        News news = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("News not found"));
        return new NewsResponseDTO(news, List.of());
    }

    @Transactional
public NewsResponseDTO createNews(NewsRequestDTO dto) {
    News news = News.builder()
            .title(dto.title())
            .subtitle(dto.subtitle())
            .content(dto.content())
            .imageUrl(dto.imageUrl())
            .authorId(dto.authorId())
            .category(dto.category())
            .build();
    News savedNews = repository.save(news);
    return new NewsResponseDTO(savedNews, List.of());
}

    @Transactional
    public NewsResponseDTO updateNews(String id, NewsRequestDTO dto) {
        News news = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("News not found"));

        news.setTitle(dto.title());
        news.setSubtitle(dto.subtitle());
        news.setContent(dto.content());
        news.setImageUrl(dto.imageUrl());
        news.setAuthorId(dto.authorId());
        news.setCategory(dto.category());

        News updatedNews = repository.save(news);
        return new NewsResponseDTO(updatedNews, List.of());
    }

    @Transactional
    public void deleteNews(String id) {
        repository.deleteById(id);
    }

}
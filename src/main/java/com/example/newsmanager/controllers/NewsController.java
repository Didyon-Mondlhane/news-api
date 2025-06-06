package com.example.newsmanager.controllers;

import com.example.newsmanager.domain.news.NewsRequestDTO;
import com.example.newsmanager.domain.news.NewsResponseDTO;
import com.example.newsmanager.services.NewsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping
    public ResponseEntity<List<NewsResponseDTO>> getAllNews() {
        List<NewsResponseDTO> newsList = newsService.getAll();
        return ResponseEntity.ok(newsList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsResponseDTO> getNewsById(@PathVariable String id) {
        NewsResponseDTO news = newsService.getById(id);
        return ResponseEntity.ok(news);
    }

    @PostMapping ("/create")
    public ResponseEntity<NewsResponseDTO> createNews(@RequestBody @Valid NewsRequestDTO dto) {
        NewsResponseDTO createdNews = newsService.createNews(dto);
        return ResponseEntity.ok(createdNews);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<NewsResponseDTO> updateNews(
        @PathVariable String id,
        @RequestBody @Valid NewsRequestDTO dto
    ) {
        NewsResponseDTO updatedNews = newsService.updateNews(id, dto);
        return ResponseEntity.ok(updatedNews);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteNews(@PathVariable String id) {
        newsService.deleteNews(id);
        return ResponseEntity.noContent().build();
    }
}
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
    public ResponseEntity<List<NewsResponseDTO>> getAll() {
        return ResponseEntity.ok(newsService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsResponseDTO> getById(@PathVariable String id) {
        return ResponseEntity.ok(newsService.getById(id));
    }

    @PostMapping
    public ResponseEntity<NewsResponseDTO> create(@RequestBody @Valid NewsRequestDTO dto) {
        return ResponseEntity.ok(newsService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NewsResponseDTO> update(
        @PathVariable String id,
        @RequestBody @Valid NewsRequestDTO dto) {
        return ResponseEntity.ok(newsService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        newsService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
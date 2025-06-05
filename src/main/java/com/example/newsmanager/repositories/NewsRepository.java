package com.example.newsmanager.repositories;

import com.example.newsmanager.domain.news.News;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface NewsRepository extends JpaRepository<News, String> {
    List<News> findByCategory(String category);
    List<News> findByAuthorId(String authorId);
    List<News> findByTitleContainingIgnoreCase(String title);
    List<News> findByContentContainingIgnoreCase(String keyword);
    Optional<News> findById(String id);
    List<News> findAll();
}
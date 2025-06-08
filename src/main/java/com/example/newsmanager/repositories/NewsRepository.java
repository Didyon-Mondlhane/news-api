package com.example.newsmanager.repositories;

import com.example.newsmanager.domain.news.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface NewsRepository extends JpaRepository<News, String> {

    List<News> findByCategory(com.example.newsmanager.domain.category.Category category);
    List<News> findByAuthorId(Long authorId);
    List<News> findByTitleContainingIgnoreCase(String title);
    List<News> findByContentContainingIgnoreCase(String keyword);
    Optional<News> findById(String id);
    List<News> findAll();

    @Query("SELECT n FROM News n LEFT JOIN FETCH n.comments")
    List<News> findAllWithComments();

    @Query("SELECT n FROM News n LEFT JOIN FETCH n.comments WHERE n.id = :id")
    Optional<News> findByIdWithComments(String id);

    @Query("SELECT n FROM News n LEFT JOIN FETCH n.comments LEFT JOIN FETCH n.reactions WHERE n.id = :id")
    Optional<News> findByIdWithCommentsAndReactions(String id);
}

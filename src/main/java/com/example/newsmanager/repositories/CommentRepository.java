package com.example.newsmanager.repositories;

import com.example.newsmanager.domain.comment.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByNewsId(Long newsId);
    List<Comment> findByNewsIdAndParentIsNull(Long newsId);
    List<Comment> findByParentId(Long parentId);
    
    @Query("SELECT c FROM Comment c WHERE c.news.id = :newsId ORDER BY c.createdAt DESC")
    List<Comment> findLatestByNewsId(@Param("newsId") Long newsId, Pageable pageable);
    
    long countByNewsId(Long newsId);
}
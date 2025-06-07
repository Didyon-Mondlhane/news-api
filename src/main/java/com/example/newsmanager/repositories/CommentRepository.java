package com.example.newsmanager.repositories;

import com.example.newsmanager.domain.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByNewsIdAndParentIsNullOrderByCreatedAtDesc(Long newsId);

    List<Comment> findByParentIdOrderByCreatedAtAsc(Long parentId);

    long countByNewsId(Long newsId);

    List<Comment> findTop5ByNewsIdAndParentIsNullOrderByCreatedAtDesc(Long newsId);
}

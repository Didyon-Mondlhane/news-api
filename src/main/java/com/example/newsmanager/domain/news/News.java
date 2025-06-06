package com.example.newsmanager.domain.news;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
//import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String title;

    private String subtitle;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    private String imageUrl;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private String authorId; // FK to Author entity

    @Column(nullable = false)
    private String category; // FK to Category entity

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

//     public List<NewsImage> getImage() {
    
//         throw new UnsupportedOperationException("Unimplemented method 'getImage'");
//     }
 }

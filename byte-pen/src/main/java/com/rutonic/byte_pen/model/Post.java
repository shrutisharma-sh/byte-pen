package com.rutonic.byte_pen.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "posts")
@Data
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDate createdAt = LocalDate.now(); // Fixed field naming (lowercase 'c')
    private boolean isPublished;

    @ManyToOne
    @JoinColumn(name = "user_id") // Added explicit join column
    private User author; // Changed from String to User

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setPublished(boolean published) {
        isPublished = published;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public boolean isPublished() {
        return isPublished;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Long getId() {
        return id;
    }

    // Removed redundant setters/getters as we're using @Data
}
package com.rutonic.byte_pen.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "users") // Changed from "user" as "user" is often a reserved keyword
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Changed from Integer to Long for consistency

    @Column(nullable = false, unique = true) // Added unique constraint
    private String username; // Fixed capitalization (lowercase 'u')

    @Column(nullable = false)
    private String password;

    private String role = "ROLE_USER";

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Post> posts;

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
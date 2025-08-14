package com.rutonic.byte_pen.repository;

import com.rutonic.byte_pen.model.Post;

import java.util.List;

import com.rutonic.byte_pen.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {

    List<Post> findByIsPublicTrue();

    User findByUsername(String username);
}

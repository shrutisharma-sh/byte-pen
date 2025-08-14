package com.rutonic.byte_pen.service;


import com.rutonic.byte_pen.model.Post;
import com.rutonic.byte_pen.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepo;

    public PostService(PostRepository postRepo) {
        this.postRepo = postRepo;
    }

    public List<Post> getAllPosts() {
        return postRepo.findAll();
    }

    public Post createPost(Post post) {
        return postRepo.save(post);
    }

    public Optional<Post> getPostById(Long id) {
        return postRepo.findById(id);
    }

    public List<Post> getPublicPosts() {
        return postRepo.findByIsPublicTrue();
    }

    public void deletePost(Long id) {
        postRepo.deleteById(id);
    }
}
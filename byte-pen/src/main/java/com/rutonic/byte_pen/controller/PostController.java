package com.rutonic.byte_pen.controller;

import com.rutonic.byte_pen.model.Post;
import com.rutonic.byte_pen.model.User;
import com.rutonic.byte_pen.repository.PostRepository;
import com.rutonic.byte_pen.service.PostService;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PostController {

    private final PostRepository postRepo;

    private final PostService postService;

    public PostController(PostRepository postRepo, PostService postService) {
        this.postRepo = postRepo;
        this.postService = postService;
    }

    @GetMapping("/posts")
    public String viewPublicPosts(Model model) {
        List<Post> posts = postService.getPublicPosts();
        model.addAttribute("posts", posts);
        return "posts";
    }

    @GetMapping("/posts/{id}")
    public String getPostById(@PathVariable Long id, Model model) throws Exception {
        Optional<Post> post = postService.getPostById(id);
        if (post.isPresent()) {
            model.addAttribute("post", post.get());
            return "post";
        } else {
            return "redirect:/posts";
        }
    }

    @GetMapping("/posts/new")
    public String createPostForm(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        model.addAttribute("post", new Post());
        return "create_post";
    }

    @PostMapping("/posts")
    public String savePost(@ModelAttribute Post post, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        String username = principal.getName();
        User author = postRepo.findByUsername(username);
        post.setAuthor(author);
        post.setCreatedAt(LocalDate.now());
        postService.createPost(post);
        return "redirect:/posts";
    }

    @GetMapping("posts/edit/{id}")
    public String editPostForm(@PathVariable Long id, Model model, Principal principal) throws Exception {
        Post post = postService.getPostById(id)
                .orElseThrow(() -> new Exception("Post not found"));
        if (!post.getAuthor().equals(principal.getName())) {
            return "redirect:/posts";
        }
        model.addAttribute("post", post);
        return "post_form";
    }

    @GetMapping("posts/delete/{id}")
    public String deletePost(@PathVariable Long id, Principal principal) throws Exception {
        Post post = postService.getPostById(id)
                .orElseThrow(() -> new Exception("Post not found"));
        if (!post.getAuthor().equals(principal.getName())) {
            return "redirect:/posts";
        }
        postService.deletePost(id);
        return "redirect:/posts";
    }
}
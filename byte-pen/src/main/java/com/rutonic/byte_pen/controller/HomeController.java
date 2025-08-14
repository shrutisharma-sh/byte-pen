package com.rutonic.byte_pen.controller;

import com.rutonic.byte_pen.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final PostService postService;

    public HomeController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/home")
    public String HomePage(Model model) {

        model.addAttribute("posts", postService.getPublicPosts());
        return "home";
    }
}

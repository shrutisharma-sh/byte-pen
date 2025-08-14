package com.rutonic.byte_pen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.rutonic.byte_pen.model.User;
import com.rutonic.byte_pen.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/signup")
    public String signupPage() {
        return "signup";
    }

    @PostMapping("/signup")
    public String registerUser(@ModelAttribute User user) {
        if (userRepo.findByUsername(user.getUsername()) != null) {
            return "redirect:/auth/signup?error"; // Redirect if username already exists
        }
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encode password
        userRepo.save(user); // Save user to the database
        return "redirect:/auth/login"; // Redirect to login page after successful registration
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/logout")
    public String logoutPage() {
        return "logout";
    }
}
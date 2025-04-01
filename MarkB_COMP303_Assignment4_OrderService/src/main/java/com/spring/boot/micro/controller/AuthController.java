package com.spring.boot.micro.controller;

import com.spring.boot.micro.model.User;
import com.spring.boot.micro.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/")
    public String homePage() {
        return "home";
    }

    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String signupSubmit(@ModelAttribute User user, Model model) {
        if (authService.userExists(user.getUsername(), user.getAccountId())) {
            model.addAttribute("error", "Username or Account ID already exists.");
            return "signup";
        }

        String message = authService.registerUser(user);
        model.addAttribute("message", message);
        model.addAttribute("username", user.getUsername());
        return "welcome"; // directs to welcome.html after signup
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute User user, Model model) {
        User found = authService.authenticate(user.getUsername(), user.getPassword());
        if (found != null) {
            model.addAttribute("username", found.getUsername());
            model.addAttribute("accountId", found.getAccountId());
            return "redirect:/order";
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "login";
        }
    }
}

package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    /**
     * Login endpoint.
     * Expects username and password parameters.
     * Example: POST /auth/login?username=user1&password=pass123
     */
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPasswordHash().equals(SecurityUtil.hashPassword(password))) {
            session.setAttribute("user", user);
            session.setAttribute("username", user.getUsername());
            System.out.println("User " + user.getUsername() + " logged in with session ID: " + session.getId());
            return "Login successful";
        } else {
            return "Invalid username or password";
        }
    }


    /**
     * Logout endpoint.
     * Example: POST /auth/logout
     */
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "Logged out successfully";
    }
}

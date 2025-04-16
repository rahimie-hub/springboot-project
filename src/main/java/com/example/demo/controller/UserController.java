package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.session.SessionRegistryListener;
import com.example.demo.util.SecurityUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller to manage user operations:
 * - Adding a user
 * - Retrieving active sessions (logged in users)
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    /**
     * Adds a new user. The password is hashed before storing.
     * The username is also stored in the session as a simple way to track logged-in users.
     *
     * Example: POST /user/add?username=user1&password=pass123
     *
     * @param username the username for the new user
     * @param password the plain text password, which will be hashed
     * @param session  the HTTP session used to store logged-in user info
     * @return A confirmation message regarding the user addition
     */
    @PostMapping("/add")
    public String addUser(@RequestParam String username,
                          @RequestParam String password,
                          HttpSession session) {
    	// Check if user with the given username already exists.
        User existingUser = userRepository.findByUsername(username);
        if (existingUser != null) {
            return "User already exists.";
        }
        // Hash the plain text password using SHA-256.
        String hashedPassword = SecurityUtil.hashPassword(password);
        // Create and save the new user.
        User user = new User();
        user.setUsername(username);
        user.setPasswordHash(hashedPassword);
        userRepository.save(user);

        // Store the username in the session
        session.setAttribute("user", username);
        System.out.println("User " + username + " logged in with session ID: " + session.getId());
        return "User added and logged in successfully.";
    }
    
    /**
     * Returns the list of active usernames from the current sessions.
     *
     * Example: GET /user/active-sessions
     *
     * @return A list of usernames (as Strings) associated with active sessions.
     */
    @GetMapping("/active-sessions")
    public List<String> getActiveSessions() {
        List<String> activeUsers = new ArrayList<>();
        for (HttpSession s : SessionRegistryListener.getActiveSessions()) {
            Object user = s.getAttribute("username");
            if (user != null) {
                activeUsers.add(user.toString());
            }
        }
        System.out.println("Active users: " + activeUsers);
        return activeUsers;
    }

}

package com.skywins.Job.Application.user.impl;

import com.skywins.Job.Application.user.User;
import com.skywins.Job.Application.user.UserRepository;
import com.skywins.Job.Application.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UserServiceimpl implements UserService {
    @Autowired
    private UserRepository userRepository;


    public User registerUser(User user) {
        // Check if username or email already exists
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists!");
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists!");
        }

        // Hash the password before saving
        user.setPassword(hashPassword(user.getPassword()));

        // Save the user
        return userRepository.save(user);
    }

    @Override
    public User loginUser(String username, String password) {
        // Find the user by username
        return userRepository.findByUsername(username)
                .filter(user -> user.getPassword().equals(hashPassword(password))) // Validate password
                .orElseThrow(() -> new RuntimeException("Invalid username or password!"));
    }


    // Helper method to hash passwords
    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error while hashing password", e);
        }
    }

}

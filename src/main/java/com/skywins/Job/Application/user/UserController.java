package com.skywins.Job.Application.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/auth")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public User signup(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public User login(@RequestBody User loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        return userService.loginUser(username, password);
    }
}


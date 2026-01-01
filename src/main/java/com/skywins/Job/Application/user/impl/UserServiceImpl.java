package com.skywins.Job.Application.user.impl;

import com.skywins.Job.Application.auth.AuthResponse;
import com.skywins.Job.Application.auth.LoginRequest;
import com.skywins.Job.Application.auth.SignupRequest;
import com.skywins.Job.Application.security.JwtUtil;
import com.skywins.Job.Application.user.User;
import com.skywins.Job.Application.user.UserRepository;
import com.skywins.Job.Application.user.UserService;
import java.util.UUID;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private UserRepository userRepository;
  private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public AuthResponse signup(SignupRequest req) {
    if (userRepository.findByEmail(req.getEmail()).isPresent()) {
      throw new IllegalArgumentException("Email already exists");
    }

    User user = new User();
    user.setName(req.getName());
    user.setEmail(req.getEmail().toLowerCase());
    user.setRole(req.getRole() == null ? "USER" : req.getRole());
    user.setCompanyId(req.getCompanyId());
    user.setPassword(encoder.encode(req.getPassword()));

    User savedUser = userRepository.save(user);

    String token = JwtUtil.generateToken(savedUser.getId(), savedUser.getEmail(), savedUser.getRole());

    return new AuthResponse("User created", token, savedUser.getId(), savedUser.getRole());
  }

  public AuthResponse login(LoginRequest req) {

    User user =
        userRepository
            .findByEmail(req.getEmail().toLowerCase())
            .orElseThrow(() -> new IllegalArgumentException("Invalid email"));

    if (!encoder.matches(req.getPassword(), user.getPassword())) {
      throw new IllegalArgumentException("Invalid credentials");
    }

    String token = JwtUtil.generateToken(user.getId(), user.getEmail(), user.getRole());

    return new AuthResponse("Login successful", token, user.getId(), user.getRole());
  }
}

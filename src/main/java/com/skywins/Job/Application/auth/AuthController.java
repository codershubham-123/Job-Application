package com.skywins.Job.Application.auth;

import com.skywins.Job.Application.user.UserService;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/auth")
public class AuthController {

  private final UserService userService;

  public AuthController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/signup")
  public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) {
    try {
      AuthResponse resp = userService.signup(signupRequest);
      return ResponseEntity.ok(resp);
    } catch (IllegalArgumentException ex) {
      return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
    } catch (Exception e) {
      return ResponseEntity.status(500).body(Map.of("error", "Internal Server Error"));
    }
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
    try {
      AuthResponse resp = userService.login(loginRequest);
      return ResponseEntity.ok(resp);
    } catch (IllegalArgumentException ex) {
      return ResponseEntity.status(401).body(Map.of("error", ex.getMessage()));
    } catch (Exception e) {
      return ResponseEntity.status(500).body(Map.of("error", "Internal Server Error"));
    }
  }
}

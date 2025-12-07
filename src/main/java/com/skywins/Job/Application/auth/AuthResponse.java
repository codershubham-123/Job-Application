package com.skywins.Job.Application.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
  private String message;
  private String token;
  private Long userid;
  private String role;
}

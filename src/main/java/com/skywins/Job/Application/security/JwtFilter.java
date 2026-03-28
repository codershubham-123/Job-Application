package com.skywins.Job.Application.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Allow preflight CORS requests
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");

        // Block request only if token missing
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Missing Authorization header");
            return;
        }

        String token = authHeader.substring(7);

        if(!JwtUtil.isValidToken(token)){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid or expired token");
            return;
        }

        Long userId = JwtUtil.extractUseruserId(token);
        String email = JwtUtil.extactUserEmail(token);
        String role = JwtUtil.extractUserRole(token);

        request.setAttribute("userId", userId);
        request.setAttribute("email", email);
        request.setAttribute("role", role);

        filterChain.doFilter(request, response);

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {

        return request.getRequestURI().startsWith("/api/auth")
                || request.getMethod().equalsIgnoreCase("OPTIONS");
    }
}

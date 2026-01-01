package com.skywins.Job.Application.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {

    // Secret key used to sign JWT
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Token validity
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 12;

    //    Generate JWT token
    public static String generateToken(Long userId, String email, String role) {
        return Jwts.builder().setSubject(email)
                .claim("userId", userId)    // main identity
                .claim("role", role)        // custom data
                .setIssuedAt(new Date())        // custom data
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY)
                .compact();
    }

    //    Extract all claims from token
    public static Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token).getBody();
    }

    //    Get email (subject) from token
    public static String extactUserEmail(String token){
        return extractAllClaims(token).getSubject();
    }

    //    Get userId from token
    public static Long extractUseruserId(String token){
        return extractAllClaims(token).get("userId", Long.class);
    }

    //    Get role from token
    public static String extractUserRole(String token){
        return extractAllClaims(token).get("role", String.class);
    }

    //    Check if token expired
    public static boolean isTokenExpired(String token){
        Date expiration = extractAllClaims(token).getExpiration();
        return expiration.before(new Date());
    }

    //    Validate Token
    public static boolean isValidToken(String token){
        try{
            extractAllClaims(token);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}




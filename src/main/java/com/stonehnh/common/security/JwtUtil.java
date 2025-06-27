package com.stonehnh.common.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String jwtSecret;

    private final long ACCESS_EXP = 15 * 60 * 1000;        // 15 phút
    private final long REFRESH_EXP = 7 * 24 * 60 * 60 * 1000; // 7 ngày

    // Tạo token chung
    public String generateToken(String email, List<String> roles, long expiration) {
        return Jwts.builder()
                .setSubject(email)
                .claim("roles", roles) // Ghi roles dạng JSON array
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    // Access token
    public String generateAccessToken(String email, List<String> roles) {
        return generateToken(email, roles, ACCESS_EXP);
    }

    // Refresh token
    public String generateRefreshToken(String email, List<String> roles) {
        return generateToken(email, roles, REFRESH_EXP);
    }

    // Kiểm tra hợp lệ
    public boolean isValid(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    // Lấy email từ token
    public String getEmailFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Lấy danh sách role từ token
    @SuppressWarnings("unchecked")
    public List<String> getRolesFromToken(String token) {
        return (List<String>) Jwts.parser()
                .setSigningKey(jwtSecret)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("roles", List.class);
    }
}

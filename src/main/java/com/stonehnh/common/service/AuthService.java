package com.stonehnh.common.service;

import com.stonehnh.customer.dto.response.CustomerResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface AuthService {
    ResponseEntity<?> login(Map<String, String> req);
    ResponseEntity<?> refresh(String token);
    ResponseEntity<?> logout();
    ResponseEntity<?> getAccount(String authHeader);
    ResponseEntity<?> googleLogin(Map<String, String> req);
}
package com.stonehnh.common.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.stonehnh.common.security.JwtUtil;
import com.stonehnh.customer.dto.request.CreationCustomerDto;
import com.stonehnh.customer.dto.response.CustomerResponseDto;
import com.stonehnh.customer.service.CustomerRoleService;
import com.stonehnh.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtil jwtUtil;

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerRoleService customerRoleService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> req) {
        String email = req.get("email");
        String password = req.get("password");

        if (!customerService.checkLoginByEmailAndPassword(email, password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Sai tài khoản hoặc mật khẩu");
        }

        // Lấy danh sách role của user
        List<String> roles = customerService.getRolesByEmail(email);

        // Tạo token từ danh sách role
        String accessToken = jwtUtil.generateAccessToken(email, roles);
        String refreshToken = jwtUtil.generateRefreshToken(email, roles);

        // Lấy thông tin user (DTO)
        CustomerResponseDto loggedUser = customerService.findCustomerByEmail(email);

        // Lưu refresh token vào cookie (httpOnly)
        ResponseCookie cookie = ResponseCookie.from("refreshToken", refreshToken)
                .httpOnly(true)
                .path("/api/auth/refresh")
                .maxAge(Duration.ofDays(7))
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(Map.of(
                        "accessToken", accessToken,
                        "user", loggedUser,
                        "success", true,
                        "message", "Đăng nhập thành công"
                ));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@CookieValue(value = "refreshToken", required = false) String token) {
        if (token == null || !jwtUtil.isValid(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Refresh token không hợp lệ");
        }

        String email = jwtUtil.getEmailFromToken(token);
        List<String> roles = jwtUtil.getRolesFromToken(token);
        String newAccessToken = jwtUtil.generateAccessToken(email, roles);

        return ResponseEntity.ok(Map.of("accessToken", newAccessToken));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        ResponseCookie cookie = ResponseCookie.from("refreshToken", "")
                .httpOnly(true)
                .path("/api/auth/refresh")
                .maxAge(0)
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(Map.of("message", "Đăng xuất thành công"));
    }

    @GetMapping("/account")
    public ResponseEntity<?> account(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Thiếu token hoặc sai định dạng");
        }

        String token = authHeader.substring(7); // Bỏ "Bearer "
        if (!jwtUtil.isValid(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token không hợp lệ");
        }

        String email = jwtUtil.getEmailFromToken(token);
        CustomerResponseDto user = customerService.findCustomerByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy người dùng");
        }

        return ResponseEntity.ok().body(Map.of(
                "message", "Lấy Account theo access-token thành công",
                "success", true,
                "data", user)
        );
    }

    @PostMapping("/google")
    public ResponseEntity<?> googleLogin(@RequestBody Map<String, String> req) {
        try {
            String idTokenString = req.get("credential");
            if (idTokenString == null) {
                return ResponseEntity.badRequest().body(Map.of("message", "Missing credential"));
            }

            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                    new NetHttpTransport(),
                    JacksonFactory.getDefaultInstance())
                    .setAudience(Collections.singletonList("103379412399-bqodeo39itadsdjvmf1i29rg3av6uctb.apps.googleusercontent.com"))
                    .build();

            GoogleIdToken idToken = verifier.verify(idTokenString);
            if (idToken == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Invalid ID token"));
            }

            GoogleIdToken.Payload payload = idToken.getPayload();
            String email = payload.getEmail();
            String name = (String) payload.get("name");
            String pictureUrl = (String) payload.get("picture");

            // Kiểm tra user tồn tại
            CustomerResponseDto customer = customerService.findCustomerByEmail(email);
            if (customer == null) {
                // Tạo user mới
                CreationCustomerDto newUser = new CreationCustomerDto();
                newUser.setCustomerId("GOOGLE_ID");
                newUser.setCustomerName(name);
                newUser.setEmail(email);
                newUser.setPhoneNumber("GOOGLE_PHONE_NUMBER");
                newUser.setPassword("GOOGLE_PASSWORD");
                newUser.setCustomerAddress("GOOGLE_ADDRESS");
                newUser.setCreatedDate(new Date());
                newUser.setCustomerPicture(pictureUrl);
                newUser.setVerifyStatus(true);
                newUser.setAccountStatus(true);

                customer = customerService.createNewCustomerWithRole(newUser, List.of("R01"));

                if (customer == null) {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body(Map.of("message", "Không thể tạo tài khoản mới"));
                }
            }

            String accessToken = jwtUtil.generateAccessToken(email, customer.getRoleName());
            String refreshToken = jwtUtil.generateRefreshToken(email, customer.getRoleName());

            ResponseCookie cookie = ResponseCookie.from("refreshToken", refreshToken)
                    .httpOnly(true)
                    .path("/api/auth/refresh")
                    .maxAge(Duration.ofDays(7))
                    .build();

            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE, cookie.toString())
                    .body(Map.of(
                            "accessToken", accessToken,
                            "user", customer,
                            "message", "Đăng nhập Google thành công"
                    ));
        } catch (Exception e) {
            e.printStackTrace(); // Log lỗi ra console
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Lỗi server: " + e.getMessage()));
        }
    }
}

package com.stonehnh.common.service.impl;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.stonehnh.common.enums.ErrorCode;
import com.stonehnh.common.security.JwtUtil;
import com.stonehnh.common.service.AuthService;
import com.stonehnh.customer.dto.request.CreationCustomerDto;
import com.stonehnh.customer.dto.response.CustomerResponseDto;
import com.stonehnh.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtUtil jwtUtil;
    private final CustomerService customerService;
    private static final String GOOGLE_CLIENT_ID = "103379412399-bqodeo39itadsdjvmf1i29rg3av6uctb.apps.googleusercontent.com";

    @Override
    public ResponseEntity<?> login(Map<String, String> req) {
        String email = req.get("email");
        String password = req.get("password");

        if (!customerService.checkLoginByEmailAndPassword(email, password)) {
            return buildErrorResponse(HttpStatus.UNAUTHORIZED, ErrorCode.INVALID_CREDENTIALS);
        }

        List<String> roles = customerService.getRolesByEmail(email);
        String accessToken = jwtUtil.generateAccessToken(email, roles);
        String refreshToken = jwtUtil.generateRefreshToken(email, roles);
        CustomerResponseDto loggedUser = customerService.findCustomerByEmail(email);

        ResponseCookie cookie = createRefreshTokenCookie(refreshToken);

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(createLoginResponse(accessToken, loggedUser));
    }

    @Override
    public ResponseEntity<?> refresh(String token) {
        if (token == null || !jwtUtil.isValid(token)) {
            return buildErrorResponse(HttpStatus.UNAUTHORIZED, ErrorCode.INVALID_REFRESH_TOKEN);
        }

        String email = jwtUtil.getEmailFromToken(token);
        List<String> roles = jwtUtil.getRolesFromToken(token);
        String newAccessToken = jwtUtil.generateAccessToken(email, roles);

        return ResponseEntity.ok(Map.of("accessToken", newAccessToken));
    }

    @Override
    public ResponseEntity<?> logout() {
        ResponseCookie cookie = ResponseCookie.from("refreshToken", "")
                .httpOnly(true)
                .path("/api/auth/refresh")
                .maxAge(0)
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(Map.of(
                        "message", ErrorCode.LOGOUT_SUCCESS.getMessage(),
                        "success", true
                ));
    }

    @Override
    public ResponseEntity<?> getAccount(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return buildErrorResponse(HttpStatus.UNAUTHORIZED, ErrorCode.MISSING_OR_INVALID_TOKEN);
        }

        String token = authHeader.substring(7);
        if (!jwtUtil.isValid(token)) {
            return buildErrorResponse(HttpStatus.UNAUTHORIZED, ErrorCode.INVALID_TOKEN);
        }

        String email = jwtUtil.getEmailFromToken(token);
        CustomerResponseDto user = customerService.findCustomerByEmail(email);
        if (user == null) {
            return buildErrorResponse(HttpStatus.NOT_FOUND, ErrorCode.CUSTOMER_NOT_FOUND);
        }

        return ResponseEntity.ok().body(createAccountResponse(user));
    }

    @Override
    public ResponseEntity<?> googleLogin(Map<String, String> req) {
        try {
            String idTokenString = req.get("credential");
            if (idTokenString == null) {
                return buildErrorResponse(HttpStatus.BAD_REQUEST, ErrorCode.MISSING_CREDENTIAL);
            }

            GoogleIdToken idToken = verifyGoogleToken(idTokenString);
            if (idToken == null) {
                return buildErrorResponse(HttpStatus.UNAUTHORIZED, ErrorCode.INVALID_ID_TOKEN);
            }

            GoogleIdToken.Payload payload = idToken.getPayload();
            String email = payload.getEmail();
            String name = (String) payload.get("name");
            String pictureUrl = (String) payload.get("picture");

            CustomerResponseDto customer = customerService.findCustomerByEmail(email);
            if (customer == null) {
                customer = createGoogleUser(email, name, pictureUrl);
                if (customer == null) {
                    return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.FAILED_TO_CREATE_ACCOUNT);
                }
            }

            String accessToken = jwtUtil.generateAccessToken(email, customer.getRoleName());
            String refreshToken = jwtUtil.generateRefreshToken(email, customer.getRoleName());

            ResponseCookie cookie = createRefreshTokenCookie(refreshToken);

            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE, cookie.toString())
                    .body(createGoogleLoginResponse(accessToken, customer));
        } catch (Exception e) {
            return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.SERVER_ERROR);
        }
    }

    private ResponseEntity<?> buildErrorResponse(HttpStatus status, ErrorCode errorCode) {
        return ResponseEntity.status(status)
                .body(Map.of(
                        "message", errorCode.getMessage(),
                        "code", errorCode.getCode(),
                        "success", false
                ));
    }

    private GoogleIdToken verifyGoogleToken(String idTokenString) throws Exception {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                new NetHttpTransport(),
                JacksonFactory.getDefaultInstance())
                .setAudience(Collections.singletonList(GOOGLE_CLIENT_ID))
                .build();
        return verifier.verify(idTokenString);
    }

    private CustomerResponseDto createGoogleUser(String email, String name, String pictureUrl) {
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

        return customerService.createNewCustomerWithRole(newUser, List.of("R01"));
    }

    private ResponseCookie createRefreshTokenCookie(String refreshToken) {
        return ResponseCookie.from("refreshToken", refreshToken)
                .httpOnly(true)
                .path("/api/auth/refresh")
                .maxAge(Duration.ofDays(7))
                .build();
    }

    private Map<String, Object> createLoginResponse(String accessToken, CustomerResponseDto user) {
        return Map.of(
                "accessToken", accessToken,
                "user", user,
                "success", true,
                "message", ErrorCode.LOGIN_SUCCESS.getMessage()
        );
    }

    private Map<String, Object> createAccountResponse(CustomerResponseDto user) {
        return Map.of(
                "message", ErrorCode.GET_ACCOUNT_SUCCESS.getMessage(),
                "success", true,
                "data", user
        );
    }

    private Map<String, Object> createGoogleLoginResponse(String accessToken, CustomerResponseDto user) {
        return Map.of(
                "accessToken", accessToken,
                "user", user,
                "message", ErrorCode.GOOGLE_LOGIN_SUCCESS.getMessage(),
                "success", true
        );
    }
}
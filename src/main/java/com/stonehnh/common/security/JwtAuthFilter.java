package com.stonehnh.common.security;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import io.jsonwebtoken.JwtException;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        List<String> whiteList = List.of(
                "/api/v1/auth/**",
                "/api/v1/homestay/**",
                "/api/v1/customers/register",
                "/api/v1/customers/send-verification-code",
                "/api/v1/customers/verify-code",
                "/api/v1/customers/register"
        );

        for (String pattern : whiteList) {
            if (new AntPathRequestMatcher(pattern).matches(request)) {
                filterChain.doFilter(request, response);
                return;
            }
        }

        String authHeader = request.getHeader("Authorization");

        if (!StringUtils.hasText(authHeader) || !authHeader.startsWith("Bearer ")) {
            unauthorized(response, "Missing token");
            return;
        }

        String token = authHeader.substring(7); // Bỏ "Barear "

        try {
            if (!jwtUtil.isValid(token)) {
                unauthorized(response, "Invalid token");
                return;
            }

            String email = jwtUtil.getEmailFromToken(token);
            List<String> roles = jwtUtil.getRolesFromToken(token);

            // Chuyển roles thành List<GrantedAuthority>
            List<SimpleGrantedAuthority> authorities = roles.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(email, null, authorities);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (JwtException e) {
            unauthorized(response, "Expired or invalid token");
            return;
        }

        filterChain.doFilter(request, response);
    }

    private void unauthorized(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write("""
            {
              "error": "Unauthorized",
              "message": "%s"
            }
        """.formatted(message));
    }
}

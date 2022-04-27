package com.example.jwt_prac;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    //인증 성공하면 securitycontext 안에 넣는다(only authentication type)
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String accessToken = jwtUtil.resolveAccessToken(request);
        boolean isAccessTokenValid = accessToken != null && jwtUtil.validateToken(accessToken);

        try {
            if (isAccessTokenValid) {
                Authentication authentication = jwtUtil.getAuthentication(accessToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                String refreshToken = jwtUtil.resolveRefreshToken(request);
                if (refreshToken != null && jwtUtil.validateRefreshToken(refreshToken)) {
                    Authentication authentication = jwtUtil.getAuthentication(refreshToken);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (Exception e) {

        }
        filterChain.doFilter(request, response);
    }
}

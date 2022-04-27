//package com.example.jwt_prac;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor //final or nonnull 필드를 파라미터로 받는 생성자
//public class RefreshTokenService {
//    private final TokenRepository tokenRepository;
//
//    public boolean isExpiredToken(String token) {
//        return TokenRepository.existsByRefreshToken(token);
//    }
//
//    public RefreshToken addExpiredToken(String token) {
//        ExpiredRefreshToken saveToken = ExpiredRefreshToken.builder()
//                .token(token)
//                .build();
//        return tokenRepository.save(saveToken);
//    }
//
//}

package com.example.jwt_prac;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<RefreshToken, Long> {

    boolean existsByRefreshToken(String token);

    void deleteByRefreshToken(String refreshToken);
}

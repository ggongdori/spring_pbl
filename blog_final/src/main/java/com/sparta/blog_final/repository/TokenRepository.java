package com.sparta.blog_final.repository;

import com.sparta.blog_final.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<RefreshToken, Long> {
    boolean existsByRefreshToken(String token);

    void deleteByRefreshToken(String refreshToken);

}

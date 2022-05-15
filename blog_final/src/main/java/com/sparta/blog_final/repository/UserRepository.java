package com.sparta.blog_final.repository;

import com.sparta.blog_final.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
//    User findUserByUsername(String username);
    User findUserByUsername(String username);

    Optional<User> findByUsername(String username);

    Optional<User> findByNickname(String nickname);
}

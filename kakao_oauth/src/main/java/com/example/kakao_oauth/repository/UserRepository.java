package com.example.kakao_oauth.repository;

import com.example.kakao_oauth.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(String userId);

    Optional<User> findByIdAndDeletedIsFalse(Long id);

    boolean existsByEmailAndEmailCheckIsTrue(String email);

    boolean existsByNickname(String nickname);

//    boolean existsByPhoneNum(String phoneNum);

    boolean existsByEmail(String email);

}
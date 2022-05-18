package com.example.kakao_oauth.util;

import com.example.kakao_oauth.domain.user.User;
import com.example.kakao_oauth.exception.CustomException;
import com.example.kakao_oauth.exception.ErrorCode;
import com.example.kakao_oauth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUtil {

    private final UserRepository userRepository;

    public User findCurrentUser() {
        User user = userRepository.findByUserId(SecurityUtil.getCurrentMemberId())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MEMBER));

        return user;
    }

}
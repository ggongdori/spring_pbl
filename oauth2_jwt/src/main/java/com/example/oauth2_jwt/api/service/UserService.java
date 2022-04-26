package com.example.oauth2_jwt.api.service;

import com.example.oauth2_jwt.api.entity.user.User;
import com.example.oauth2_jwt.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUser(String userId) {
        return userRepository.findByUserId(userId);
    }
}

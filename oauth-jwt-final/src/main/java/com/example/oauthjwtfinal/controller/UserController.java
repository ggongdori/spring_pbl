package com.example.oauthjwtfinal.controller;

import com.example.oauthjwtfinal.domain.User;
import com.example.oauthjwtfinal.domain.UserRepository;
import com.example.oauthjwtfinal.exception.ResourceNotFoundException;
import com.example.oauthjwtfinal.security.CurrentUser;
import com.example.oauthjwtfinal.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
}
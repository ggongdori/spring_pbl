package com.sparta.blog_final.service;

import com.sparta.blog_final.advice.RestException;
import com.sparta.blog_final.domain.RefreshToken;
import com.sparta.blog_final.domain.User;

import com.sparta.blog_final.dto.LoginRequestDto;
import com.sparta.blog_final.dto.RegisterRequestDto;

import com.sparta.blog_final.dto.TokenResponseDto;
import com.sparta.blog_final.repository.TokenRepository;
import com.sparta.blog_final.repository.UserRepository;
import com.sparta.blog_final.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final TokenRepository tokenRepository;
//    private static final String ADMIN_TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";

    @Transactional
    public void registerUser(RegisterRequestDto requestDto) {

        // 회원 ID 중복 확인
        Optional<User> foundUsername = userRepository.findByUsername(requestDto.getUsername());
        if (foundUsername.isPresent()) {
            throw new RestException(HttpStatus.BAD_REQUEST, "중복된 username이 존재합니다.");
        }

        Optional<User> foundNickname = userRepository.findByNickname(requestDto.getNickname());
        if (foundNickname.isPresent()) {
            throw new RestException(HttpStatus.BAD_REQUEST, "중복된 nickname이 존재합니다.");
        }

        userRepository.save(User.builder()
                .username(requestDto.getUsername())
                .password(passwordEncoder.encode(requestDto.getPw()))
                .nickname(requestDto.getNickname())
                .build());

    }

    @Transactional
    public TokenResponseDto login(LoginRequestDto requestDto) {
        User user = userRepository.findByUsername(requestDto.getUsername())
                .orElseThrow(() -> new RestException(HttpStatus.BAD_REQUEST, "가입되지 않은 username 입니다."));
        if (!passwordEncoder.matches(requestDto.getPw(), user.getPassword())) {
            throw new RestException(HttpStatus.BAD_REQUEST, "잘못된 비밀번호입니다.");
        }
        String accessToken = jwtTokenProvider.createAccessToken(user.getUsername());
        String refreshToken = jwtTokenProvider.createRefreshToken(user.getUsername());
        tokenRepository.save(new RefreshToken(refreshToken));

        return TokenResponseDto.builder()
                .ACCESS_TOKEN(accessToken)
                .REFRESH_TOKEN(refreshToken)
                .build();
    }

    @Transactional
    public void logout(HttpServletRequest request){
        String refreshToken = jwtTokenProvider.resolveRefreshToken(request);
        tokenRepository.deleteByRefreshToken(refreshToken);
        SecurityContextHolder.clearContext();
    }

}

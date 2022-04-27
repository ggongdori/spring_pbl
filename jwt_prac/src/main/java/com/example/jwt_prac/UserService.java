package com.example.jwt_prac;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final ExpiredRefreshTokenRepository expiredRefreshTokenRepository;

    @Transactional
    public void registerUser(RegisterRequestDto requestDto) {
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new RestException(HttpStatus.BAD_REQUEST, "이미 존재하는 username입니다.");
        }
        userRepository.save(User.builder()
                .email(requestDto.getEmail())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .name(requestDto.getName())
                .roles(Collections.singletonList("ROLE_USER"))
                .build());
    }

    @Transactional
    public TokenResponseDto login(LoginRequestDto requestDto) {
        User user = userRepository.findByEmail(requestDto.getUsername())
                .orElseThrow(() -> new RestException(HttpStatus.BAD_REQUEST, "가입되지 않은 username 입니다."));
        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new RestException(HttpStatus.BAD_REQUEST, "잘못된 비밀번호입니다.");
        }
        String accessToken = jwtUtil.createAccessToken(user.getUsername(), user.getRoles());
        String refreshToken = jwtUtil.createRefreshToken(user.getUsername(), user.getRoles());
        ExpiredRefreshTokenRepository.save(new RefreshToken(refreshToken));

        return TokenResponseDto.builder()
                .ACCESS_TOKEN(accessToken)
                .REFRESH_TOKEN(refreshToken)
                .build();
    }

    @Transactional
    public void logout(HttpServletRequest request){
        String refreshToken = jwtUtil.resolveRefreshToken(request);
        ExpiredRefreshTokenRepository.deleteByExpiredRefreshToken(refreshToken);
        SecurityContextHolder.clearContext();
    }
}

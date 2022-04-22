package com.sparta.blog_final.controller;

import com.sparta.blog_final.advice.RestException;
import com.sparta.blog_final.dto.LoginRequestDto;
import com.sparta.blog_final.dto.RegisterRequestDto;
import com.sparta.blog_final.dto.TokenResponseDto;
import com.sparta.blog_final.models.Success;
import com.sparta.blog_final.repository.UserRepository;
import com.sparta.blog_final.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping("/api/login")
    public ResponseEntity<Success> login(@RequestBody LoginRequestDto requestDto, HttpServletResponse response, Errors errors) {
//        if (user != null){
//            return new ResponseEntity<>(new Success(false, "이미 로그인 중입니다."), HttpStatus.BAD_REQUEST);
//        }
        if (errors.hasErrors()) {
            for (FieldError error : errors.getFieldErrors()) {
                throw new RestException(HttpStatus.BAD_REQUEST, error.getDefaultMessage());
            }
        }

        TokenResponseDto token = userService.login(requestDto);

        response.setHeader("ACCESS_TOKEN", token.getACCESS_TOKEN());
        response.setHeader("REFRESH_TOKEN", token.getREFRESH_TOKEN());

        return new ResponseEntity<>(new Success(true, "로그인에 성공했습니다."), HttpStatus.OK);
    }

    @PostMapping("/api/logout")
    public ResponseEntity<Success> logout(HttpServletRequest request) {
        userService.logout(request);
        return new ResponseEntity<>(new Success(true, "로그아웃 성공"), HttpStatus.OK);
    }
    // 회원 가입 요청 처리
//    @PostMapping("/api/register")
//    public String registerUser(@RequestBody RegisterRequestDto requestDto) {
//        userService.registerUser(requestDto);
//        return "redirect:/api/login";
//    }

    @PostMapping("/api/register")
    public ResponseEntity<Success> registerUser(@Valid @RequestBody RegisterRequestDto requestDto, Errors errors) {

        if (errors.hasErrors()) {
            for (FieldError error : errors.getFieldErrors()) {
                throw new RestException(HttpStatus.BAD_REQUEST, error.getDefaultMessage());
            }
        }

        if (requestDto.usernameInPw(requestDto.getUsername(), requestDto.getPw())) {
            throw new RestException(HttpStatus.BAD_REQUEST, "비밀번호 안에 아이디 불가");
        } else if (!requestDto.isPwEqual(requestDto.getPw(), requestDto.getPwCheck())) {
            throw new RestException(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다");
        } else {
            userService.registerUser(requestDto);
            return new ResponseEntity<>(new Success(true, "회원가입 성공"), HttpStatus.OK);
        }
    }
}
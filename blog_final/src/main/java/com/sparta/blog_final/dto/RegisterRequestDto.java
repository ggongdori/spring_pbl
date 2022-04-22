package com.sparta.blog_final.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
public class RegisterRequestDto {

    @NotBlank(message = "아이디를 입력해주세요")
    @Pattern(regexp = "[a-zA-Z0-9]{3,20}", message = "아이디는 최소 3자 이상, 알파벳 대소문자(a~z, A~Z), 숫자(0~9)")
    private String username;
    
    @NotBlank(message = "패스워드를 입력해주세요")
    @Size(min = 4, max = 20, message = "비밀번호는 최소 4자 20자 이하")
    private String pw;

    @NotBlank(message = "비밀번호 확인을 입력해주세요")
    private String pwCheck;
    
    @NotBlank(message = "닉네임을 입력해주세요")
    private String nickname;

    @Builder
    public RegisterRequestDto(String username, String pw, String nickname) {
        this.username = username;
        this.pw = pw;
        this.nickname = nickname;
    }

    public RegisterRequestDto(String username, String pw, String pwCheck, String nickname) {
        this.username = username;
        this.pw = pw;
        this.pwCheck = pwCheck;
        this.nickname = nickname;
    }

    //패스워드 안에 username 있는지 확인
    public boolean usernameInPw(String username, String pw) {
        return pw.contains(username);
    }
    //비밀번호, 비밀번호 확인 일치 확인
    public boolean isPwEqual(String pw, String pwCheck) {
        return pw.equals(pwCheck);
    }

}

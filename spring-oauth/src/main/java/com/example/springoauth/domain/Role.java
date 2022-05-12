package com.example.springoauth.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    GUEST("ROLE_GUEST", "게스트"), //로그인(회원가입) 전
    USER("ROLE_USER", "일반 사용자"), //로그인(회원가입) 후
    ADMIN("ROLE_ADMIN", "관리자");

    private final String key;
    private final String title;

}

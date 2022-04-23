package com.sparta.test_prac.web.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor //선언된 모든 final필드 포함 생성자 만듦, final 아닌건 포함 x
public class HelloResponseDto {

    private final String name;
    private final int amount;

}

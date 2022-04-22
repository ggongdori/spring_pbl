package com.sparta.blog_final.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class TokenResponseDto {
    private String ACCESS_TOKEN;
    private String REFRESH_TOKEN;

}
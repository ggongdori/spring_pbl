package com.sparta.blog_final.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequestDto {
    private String title;
    private String username;
    private String contents;
}


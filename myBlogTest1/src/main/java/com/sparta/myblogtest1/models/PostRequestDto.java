package com.sparta.myblogtest1.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostRequestDto {
    private String title;
    private String username;
    private String contents;
    private LocalDateTime modifiedAt;
}


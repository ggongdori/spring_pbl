package com.sparta.myblogtest1.domain.dtos;

import lombok.Getter;

@Getter
public class CommentRequestDto {
    private String name;
    private String comment;


    public CommentRequestDto(String name, String comment) {
        this.name = name;
        this.comment = comment;

    }
}
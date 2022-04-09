package com.sparta.myblogtest1.domain;

import lombok.Getter;
import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;

@Getter
public class BoardRequestDto {
    private String title;
    private String name;
    private String contents;


    public BoardRequestDto(String title, String name, String contents) {
        this.title = title;
        this.name = name;
        this.contents = contents;

    }
}

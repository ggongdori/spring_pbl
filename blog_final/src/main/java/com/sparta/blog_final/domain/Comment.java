package com.sparta.blog_final.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sparta.blog_final.dto.CommentRequestDto;
import com.sparta.blog_final.dto.TokenResponseDto;
import com.sparta.blog_final.models.Timestamped;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Entity
@Getter
@NoArgsConstructor
public class Comment extends Timestamped {

    //PK 선언
    //Identity = 키값 생성을 DB에 위임하고 Auto_increment, 이거에 대해서 추가로 알아보기
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String contents;

    //순환참조 방지 (JsonBackReference)
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "POST_ID") //코멘트와 포스트 연결(테이블의 "POST_ID")
    private Post post;


    //Builder vs Constructor, 빌더는 어느 필드에 어떤 값을 넣어야 하는지 확인 가능
    //추가 개념 확인 필요!!!!!!!!!!!
    @Builder
    public Comment(String contents, String username) {
        this.contents = contents;
        this.username = username;
    }

    public Comment(CommentRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
    }

    public void update(CommentRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
    }
}

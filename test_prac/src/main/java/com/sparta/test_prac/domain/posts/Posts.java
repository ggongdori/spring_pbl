package com.sparta.test_prac.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor //기본 생성자 자동 추가 public Posts(){}
@Entity //테이블과 링크될 클래스임을 나타냄, 기본값으로 클래스카멜케이스 이름을 언더스코어 네이밍으로 테이블 이름 매칭
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column //테이블의 컬럼, 굳이 선언하지 않아도 해당 클래스의 필드는 모두 컬럼이 됨, 변경 사항 있으면 사용
    private String title;

    @Column
    private String content;

    private String author;

    //생성자 대신 빌더 추가, 넣어야 될 값을 명확히 알 수 있음
    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}

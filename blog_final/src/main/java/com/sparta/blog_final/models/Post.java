package com.sparta.blog_final.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Post extends Timestamped {


    //PK 선언
    //Identity = 키값 생성을 DB에 위임하고 Auto_increment, 이거에 대해서 추가로 알아보기
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_ID")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String contents;


    //순환참조 방지, Post 클래스에 JsonManagedReference
    //Comment 클래스에 JsonBackReference
    @JsonManagedReference
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();


    public Post(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();

    }

    public void update(PostRequestDto postRequestDto) {
        this.title = postRequestDto.getTitle();
        this.username = postRequestDto.getUsername();
        this.contents = postRequestDto.getContents();

    }

    public void addComment(Comment comment){
        this.comments.add(comment);
        comment.setPost(this);
    }
}
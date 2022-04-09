package com.sparta.myblogtest1.domain;


import com.sparta.myblogtest1.domain.dtos.BoardRequestDto;
import com.sparta.myblogtest1.domain.dtos.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Comment extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String comment;

    public Comment(String name, String comment) {
        this.name = name;
        this.comment = comment;

    }
    public Comment(CommentRequestDto commentRequestDto) {
        this.name = commentRequestDto.getName();
        this.comment = commentRequestDto.getComment();
    }
    public void update(CommentRequestDto commentRequestDto) {
        this.comment = commentRequestDto.getComment();
        this.name = commentRequestDto.getName();
    }

}

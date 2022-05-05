package com.example.upload.domain.comment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CommentResponseDto {

    private Long id;
    private String contents;
    private String nickname;
    private Long boardId;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.contents = comment.getContents();
        this.boardId = comment.getBoard().getId();

    }
}

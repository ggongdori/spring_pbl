package com.example.upload.domain.board;

import com.example.upload.domain.comment.CommentResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardResponseDto {
    private Long boardId;
    private String nickname;
    private String title;
    private String contents;
    private boolean bookmark;
    private int view;
    private List<CommentResponseDto> comment;
    private List<UploadFile> images;
    private LocalDateTime lastModifiedAt;

    public BoardResponseDto(Board board) {
        this.boardId = board.getId();
        this.nickname = board.getNickname();
        this.title = board.getTitle();
        this.contents = board.getContents();
        this.view = board.getView();
        this.bookmark = isBookmark();
        this.images = board.getImages();
        this.lastModifiedAt = board.getLastModifiedAt();
    }
}

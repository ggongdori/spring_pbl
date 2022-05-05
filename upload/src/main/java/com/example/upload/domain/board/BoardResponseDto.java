package com.example.upload.domain.board;

import com.example.upload.domain.board.Board;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardResponseDto {
    private Long id;
    private String nickname;
    private String title;
    private String contents;
//    private List<MultipartFile> images;
    private LocalDateTime lastModifiedAt;

    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.nickname = board.getNickname();
        this.title = board.getTitle();
        this.contents = board.getContents();
//        this.lastModifiedAt = board.getLastModifiedAt();
    }
}

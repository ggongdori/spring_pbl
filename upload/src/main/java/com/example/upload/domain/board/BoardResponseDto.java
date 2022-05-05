package com.example.upload.domain.board;

import com.example.upload.domain.board.Board;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardResponseDto {
    private Long id;
    private String title;
    private String contents;
    private List<MultipartFile> images;
    private LocalDateTime lastModifiedAt;

    public BoardResponseDto(Board board, List<MultipartFile> images) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.contents = board.getContents();
        this.images = images;
//        this.lastModifiedAt = board.getModifiedAt();

    }
}

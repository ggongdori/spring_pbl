package com.example.upload.domain.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardRequestDto {
    private Long boardId;
    private String title;
    private String contents;
    private String nickname;
    private List<MultipartFile> images;
//    private boolean bookmark;
//    private MultipartFile file;
}

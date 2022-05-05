package com.example.upload.controller;

import com.example.upload.domain.FileStore;
import com.example.upload.domain.board.BoardRepository;
import com.example.upload.domain.board.BoardRequestDto;
import com.example.upload.domain.board.BoardResponseDto;
import com.example.upload.domain.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class BoardController {
    private final BoardRepository boardRepository;
    private final FileStore fileStore;
    private final BoardService boardService;

    @PostMapping("/board")
    public BoardResponseDto saveBoard(BoardRequestDto requestDto) throws IOException {
        return boardService.saveBoard(requestDto);
    }
}

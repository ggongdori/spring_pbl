package com.example.upload.controller;

import com.example.upload.domain.FileStore;
import com.example.upload.domain.board.BoardRepository;
import com.example.upload.domain.board.BoardRequestDto;
import com.example.upload.domain.board.BoardResponseDto;
import com.example.upload.domain.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class BoardController {
    private final BoardRepository boardRepository;
    private final FileStore fileStore;
    private final BoardService boardService;

    @PostMapping("/board")
    public BoardResponseDto saveBoard(BoardRequestDto requestDto) throws IOException {
        return boardService.saveBoard(requestDto);
    }

//    @GetMapping("/posts/read/{id}")
//    public String read(@PathVariable Long id, Model model) {
//        BoardResponseDto dto = boardService.findById(id);
//        boardService.updateView(id); // views ++
//        model.addAttribute("posts", dto);
//        return "posts-read";
//    }

}

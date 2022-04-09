package com.sparta.myblogtest1.controller;

import com.sparta.myblogtest1.domain.Board;
import com.sparta.myblogtest1.domain.BoardRepository;
import com.sparta.myblogtest1.domain.BoardRequestDto;
import com.sparta.myblogtest1.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final BoardRepository boardRepository;

    @GetMapping("myblog/boards")
    public List<Board> getBoards() {
        return boardRepository.findAllByOrderByModifiedAtDesc();
    }

    @PostMapping("myblog/boards")
    public Board createBoard(@RequestBody BoardRequestDto requestDto) {
        Board board = new Board(requestDto);
        return boardRepository.save(board);
    }
    @PatchMapping("/myblog/boards/{id}")
    public Long updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto boardRequestDto){
        boardService.update(id, boardRequestDto);
        return id;
    }

    @DeleteMapping("/myblog/boards/{id}")
    public Long deleteBoard(@PathVariable Long id) {
        boardRepository.deleteById(id);
        return id;
    }
}
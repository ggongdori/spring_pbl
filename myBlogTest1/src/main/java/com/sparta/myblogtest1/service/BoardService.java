package com.sparta.myblogtest1.service;

import com.sparta.myblogtest1.domain.Board;
import com.sparta.myblogtest1.domain.BoardRepository;
import com.sparta.myblogtest1.domain.BoardRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Long update(Long id, BoardRequestDto boardRequestDto) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("없음")
        );
        board.update(boardRequestDto);
        return board.getId();
    }
}

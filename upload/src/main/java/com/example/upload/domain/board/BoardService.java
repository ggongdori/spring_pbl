package com.example.upload.domain.board;

import com.example.upload.domain.FileStore;
import com.example.upload.domain.Timestamped;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;
    private final FileStore fileStore;

    @Transactional
    public BoardResponseDto createBoard(BoardRequestDto requestDto) {

    }
}

package com.example.upload.domain.comment;

import com.example.upload.domain.board.Board;
import com.example.upload.domain.board.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public Long saveComment(Long id, CommentRequestDto requestDto) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다.")
        );
        requestDto.setBoard(board);
        Comment comment = requestDto.toEntity();
        commentRepository.save(comment);

        return requestDto.getId();
    }
}


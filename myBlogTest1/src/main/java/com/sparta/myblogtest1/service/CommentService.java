package com.sparta.myblogtest1.service;

import com.sparta.myblogtest1.domain.Comment;
import com.sparta.myblogtest1.domain.dtos.CommentRequestDto;
import com.sparta.myblogtest1.domain.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;

    @Transactional
    public Long update(Long id, CommentRequestDto commentRequestDto) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("댓글 내용을 입력해주세요!")
        );
        comment.update(commentRequestDto);
        return comment.getId();
    }
}

package com.sparta.myblogtest1.controller;


import com.sparta.myblogtest1.domain.Comment;
import com.sparta.myblogtest1.domain.dtos.CommentRequestDto;
import com.sparta.myblogtest1.domain.repository.CommentRepository;
import com.sparta.myblogtest1.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final CommentRepository commentRepository;

    @GetMapping("myblog/comments")
    public List<Comment> getBoards() {
        return commentRepository.findAllByOrderByModifiedAtDesc();
    }

    @PostMapping("myblog/comments")
    public Comment createComment(@RequestBody CommentRequestDto requestDto) {
        Comment comment = new Comment(requestDto);
        return commentRepository.save(comment);
    }

    @PatchMapping("/myblog/comments/{id}")
    public Long updateComment(@PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto) {
        commentService.update(id, commentRequestDto);
        return id;
    }

    @DeleteMapping("/myblog/comments/{id}")
    public Long deleteComment(@PathVariable Long id) {
        commentRepository.deleteById(id);
        return id;
    }
}
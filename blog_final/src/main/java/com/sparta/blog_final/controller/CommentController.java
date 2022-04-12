package com.sparta.blog_final.controller;


import com.sparta.blog_final.models.Comment;
import com.sparta.blog_final.models.CommentRepository;
import com.sparta.blog_final.models.CommentRequestDto;
import com.sparta.blog_final.models.Success;
import com.sparta.blog_final.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class  CommentController {
    private final CommentRepository commentRepository;
    private final CommentService commentService;

    @GetMapping("api/comments")
    public List<Comment> getComments() {
        return commentRepository.findAllByOrderByCreatedAtDesc();
    }

    @PostMapping("api/{id}/comments")
    public ResponseEntity<Success> createComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto) {
        commentService.addComment(id, requestDto);
        return new ResponseEntity<>(new Success(true, "댓글 등록 완료"), HttpStatus.OK);
    }

    @DeleteMapping("api/comments/{id}")
    public Long deleteComment(@PathVariable Long id) {
        commentRepository.deleteById(id);
        return id;
    }

    @PutMapping("api/comments/{id}")
    public Long updateComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto) {
        return commentService.updateComment(id, requestDto);
    }

}
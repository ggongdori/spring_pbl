package com.example.upload.controller;

import com.example.upload.domain.comment.CommentRequestDto;
import com.example.upload.domain.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class CommentController {
    private final CommentService commentService;
    @PostMapping("/board/{id}/comment")
    public ResponseEntity saveComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto) {
        return ResponseEntity.ok(commentService.saveComment(id, requestDto));
    }
}

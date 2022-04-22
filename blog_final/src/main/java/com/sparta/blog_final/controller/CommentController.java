package com.sparta.blog_final.controller;


//import com.sparta.blog_final.dto.CommentRequestDto;
//import com.sparta.blog_final.domain.Comment;
//import com.sparta.blog_final.repository.CommentRepository;
//import com.sparta.blog_final.models.Success;
//import com.sparta.blog_final.service.CommentService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//public class  CommentController {
//    //Repository and Service 객체 가져오기(DI)
//    private final CommentRepository commentRepository;
//    private final CommentService commentService;
//
//    //코멘트 리스트 가져오기
//    @GetMapping("api/comments")
//    public List<Comment> getComments() {
//        return commentRepository.findAllByOrderByModifiedAtDesc();
//    }
//
//    //Success == True, Comment 등록
//    //ResponseEntity : http 상태 코드 확인 후 Suceess == True 면 클라이언트에 응답
//    @PostMapping("api/{id}/comments")
//    public ResponseEntity<Success> createComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto) {
//        commentService.addComment(id, requestDto);
//        return new ResponseEntity<>(new Success(true, "댓글 등록 완료"), HttpStatus.OK);
//    }
//
//    //댓글 지우기(id별로)
//    @DeleteMapping("api/comments/{id}")
//    public Long deleteComment(@PathVariable Long id) {
//        commentRepository.deleteById(id);
//        return id;
//    }
//
//    // 코멘트 업데이트
//    @PutMapping("api/comments/{id}")
//    public Long updateComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto) {
//        return commentService.updateComment(id, requestDto);
//    }
//
//}
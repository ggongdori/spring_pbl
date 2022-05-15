package com.sparta.blog_final.controller;

import com.sparta.blog_final.dto.CommentRequestDto;
import com.sparta.blog_final.models.Success;
import com.sparta.blog_final.repository.LikeRepository;
import com.sparta.blog_final.security.PrincipalDetails;
import com.sparta.blog_final.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class LikeController {
    //Repository and Service 객체 가져오기(DI)

    private final LikeRepository likeRepository;
    private final LikeService likeService;

    @PostMapping("/post/{postId}/likes")
    public ResponseEntity<?> likes(@PathVariable long postId , @AuthenticationPrincipal PrincipalDetails principalDetails) {
        likeService.likes(postId, principalDetails.getUser().getId());
        return new ResponseEntity<>("좋아요 성공", HttpStatus.OK);
    }

    @DeleteMapping("/post/{postId}/likes")
    public ResponseEntity<?> unLikes(@PathVariable long postId , @AuthenticationPrincipal PrincipalDetails principalDetails) {
        likeService.unLikes(postId, principalDetails.getUser().getId());
        return new ResponseEntity<>("좋아요 취소 성공", HttpStatus.OK);
    }


}

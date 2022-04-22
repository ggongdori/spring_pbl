package com.sparta.blog_final.service;


import com.sparta.blog_final.advice.RestException;
import com.sparta.blog_final.domain.User;
import com.sparta.blog_final.repository.LikeRepository;
import com.sparta.blog_final.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LikeService {
    private final LikeRepository likeRepository;

    @Transactional
    public void likes(long postId, long sessionId) {
        try {
            likeRepository.likes(postId, sessionId);
        } catch (Exception e) {
            throw new RestException(HttpStatus.BAD_REQUEST, "이미 좋아요 하였습니다.");
        }
    }

    @Transactional
    public void unLikes(long postId, long sessionId) {
        likeRepository.unLikes(postId, sessionId);
    }
}

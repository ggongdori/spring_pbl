package com.sparta.myblogtest1.service;

import com.sparta.myblogtest1.models.Post;
import com.sparta.myblogtest1.models.PostRepository;
import com.sparta.myblogtest1.models.PostRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Long update(Long id, PostRequestDto postRequestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다.")
        );
        post.update(postRequestDto);
        return id;
    }
}
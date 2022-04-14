package com.sparta.blog_final.controller;

import com.sparta.blog_final.models.Post;
import com.sparta.blog_final.models.PostRepository;
import com.sparta.blog_final.models.PostRequestDto;
import com.sparta.blog_final.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {
    
    //Repository and Service 객체(DI)
    private final PostRepository postRepository;
    private final PostService postService;
    
    //등록된 글 리스트 가져오기
    @GetMapping("/api/posts")
    public List<Post> getPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    //글 등록하기
    @PostMapping("/api/posts")
    public Post createPost(@RequestBody PostRequestDto postRequestDto) {
        Post post = new Post(postRequestDto);
        postRepository.save(post);
        return post;
    }
    
    //글 삭제하기
    @DeleteMapping("api/posts/{id}")
    public Long deletePost(@PathVariable Long id) {
        postRepository.deleteById(id);
        return id;
    }
    
    //글 수정하기(Patch or Put??)
    @PutMapping("api/posts/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto) {
        return postService.update(id, postRequestDto);
    }

    
    //글 클릭하면 가져오기
    @GetMapping("api/posts/{id}")
    public Post readPost(@PathVariable Long id) {
        return postRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다")
        );
    }
}
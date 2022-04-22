package com.sparta.blog_final.controller;

import com.sparta.blog_final.domain.Post;
import com.sparta.blog_final.dto.PostRequestDto;
import com.sparta.blog_final.dto.PostResponseDto;
import com.sparta.blog_final.dto.PostUpdateRequestDto;
import com.sparta.blog_final.repository.PostRepository;
import com.sparta.blog_final.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {
    
    //Repository and Service 객체(DI)
    private final PostRepository postRepository;
    private final PostService postService;

    //홈화면
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("post", postService.findAllDesc());
        return "index";
    }
    //등록된 글 리스트 가져오기
    @GetMapping("/api/posts")
    public List<Post> getPosts() {
        return postRepository.findAllByOrderByModifiedAtDesc();
    }

    // 글 하나만 가져오기
    @GetMapping("api/posts/{id}")
    public PostResponseDto findById(@PathVariable Long id) {
        return postService.findById(id);
    }

    //글 등록하기
    @PostMapping("/api/posts")
    public Long save(@RequestBody PostRequestDto requestDto) {
        return postService.save(requestDto);
    }
    
    //글 수정하기(Patch or Put??)
    @PatchMapping("api/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostUpdateRequestDto requestDto) {
        return postService.update(id, requestDto);
    }
    
    //글 삭제하기
    @DeleteMapping("api/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postService.delete(id);
        return id;
    }
    
    

    
    //글 클릭하면 가져오기

}
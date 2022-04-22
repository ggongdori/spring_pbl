package com.sparta.blog_final.dto;


import com.sparta.blog_final.domain.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostResponseDto {
    private Long id;
    private String picture;
    private String content;


    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.picture = post.getPicture();
        this.content = post.getContent();
    }
}

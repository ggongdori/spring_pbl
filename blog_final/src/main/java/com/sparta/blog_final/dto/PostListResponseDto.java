package com.sparta.blog_final.dto;

import com.sparta.blog_final.domain.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostListResponseDto {
    private Long id;
    private String picture;
    private String content;

    private LocalDateTime modifiedAt;

    public PostListResponseDto(Post post) {
        this.id = post.getId();
        this.picture = post.getPicture();
        this.content = post.getContent();
        this.modifiedAt = post.getModifiedAt();
    }
}

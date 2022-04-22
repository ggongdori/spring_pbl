package com.sparta.blog_final.dto;

import com.sparta.blog_final.domain.Post;
import com.sparta.blog_final.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class PostRequestDto {
    private Long id;
    private String picture;
    private String content;
    private User user;

    @Builder
    public PostRequestDto(String picture, String content, User user) {
        this.picture = picture;
        this.content = content;
        this.user = user;

    }

    public Post toEntity() {
        return Post.builder()
                .id(id)
                .picture(picture)
                .content(content)
                .user(user)
                .build();
    }

}
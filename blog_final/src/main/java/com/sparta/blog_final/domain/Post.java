package com.sparta.blog_final.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sparta.blog_final.models.Timestamped;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Entity
@NoArgsConstructor
public class Post extends Timestamped {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_id")
    private Long id;

    @Column(nullable = false)
    private String picture;

    @Column(nullable = false)
    private String content;

    @JsonIgnoreProperties({"post"})
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Like> likeList;

    @JsonIgnoreProperties({"postList"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Transient
    private long likeCount;


    @Builder
    public Post(String picture, String content, User user, Boolean isDeleted, long likeCount) {
        this.picture = picture;
        this.content = content;
        this.user = user;
        this.likeCount = likeCount;
    }

    public void update(String picture, String content) {
        this.picture = picture;
        this.content = content;
    }
    public void updateLikeCount(long likeCount) {
        this.likeCount = likeCount;
    }


}
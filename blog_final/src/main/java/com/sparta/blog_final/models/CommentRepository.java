package com.sparta.blog_final.models;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    //CreatedAt -> ModifiedAt, 댓글 수정 후 새로 정렬, 근데 안 됨, 아마도 프론트 단에서 ModifiedAt 값을 받지 않아서
    List<Comment> findAllByOrderByModifiedAtDesc();
}
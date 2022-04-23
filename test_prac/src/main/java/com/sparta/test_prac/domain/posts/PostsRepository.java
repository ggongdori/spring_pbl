package com.sparta.test_prac.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

/*
mybatis에서의 Dao, DB Layer 접근자
 */
public interface PostsRepository extends JpaRepository<Posts, Long> {


}

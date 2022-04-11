package com.sparta.blog_final;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BlogFinalApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogFinalApplication.class, args);
    }

}

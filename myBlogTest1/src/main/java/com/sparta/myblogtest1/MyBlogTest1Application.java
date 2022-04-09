package com.sparta.myblogtest1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MyBlogTest1Application {

    public static void main(String[] args) {
        SpringApplication.run(MyBlogTest1Application.class, args);
    }

}

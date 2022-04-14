package com.sparta.blog_final.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    // 홈으로 이동
    @RequestMapping(value = "/")
    public String home() {
        return "index";
    }


    //글 등록
    @RequestMapping(value = "/post")
    public String post() {
        return "post";
    }

    //글쓰기 페이지로
    @RequestMapping(value = "/create")
    public String create() {return "create";
    }
}
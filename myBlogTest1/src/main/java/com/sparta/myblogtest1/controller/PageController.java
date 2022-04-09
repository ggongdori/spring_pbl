package com.sparta.myblogtest1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping(value = "/")
    public String home() {
        return "index.html";
    }

    @RequestMapping(value = "/post")
    public String post() {
        return "post.html";
    }

    @RequestMapping(value = "/create")
    public String create() {
        return "create.html";
    }
}
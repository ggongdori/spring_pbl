package com.example.springoauth;

import org.springframework.web.bind.annotation.GetMapping;

public class TestApi {
    @GetMapping("/test")
    public String index() {
        return "Hello World";
    }
}

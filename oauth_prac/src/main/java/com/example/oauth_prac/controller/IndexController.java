package com.example.oauth_prac.controller;

import com.example.oauth_prac.config.auth.LoginUser;
import com.example.oauth_prac.config.auth.dto.SessionUser;
//import com.example.oauth_prac.config.auth.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@RequiredArgsConstructor
@Controller
public class IndexController {
//    private final PostsService postsService;
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
//        model.addAttribute("posts", postsService.findAllDesc());
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }
}

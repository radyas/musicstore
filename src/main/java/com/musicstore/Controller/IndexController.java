package com.musicstore.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String root(){
        return "index";
    }
//
//    @GetMapping("/register")
//    public String register(){
//        return "admin/register";
//    }
//
//    @GetMapping("/login")
//    public String login(Model model){
//        return "admin/login";
//    }

//    @GetMapping("/error")
//    public String error(){
//        return "admin/error";
//    }

    @GetMapping("/news")
    public String news(){
        return "blog";
    }

    @GetMapping("/artists")
    public String artists(){
        return "artists";
    }

    @GetMapping("/artist")
    public String artist(){
        return "artist";
    }

    @GetMapping("/contactus")
    public String contact(){
        return "contact";
    }
}

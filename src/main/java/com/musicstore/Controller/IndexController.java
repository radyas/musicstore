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

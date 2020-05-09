package com.musicstore.Controller;

import com.musicstore.Model.News;
import com.musicstore.Repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private NewsRepository newsRepository;

    @GetMapping("/")
    public String root(){
        return "index";
    }

    @RequestMapping(value="/news", method = RequestMethod.GET)
    public ModelAndView contactList(){
        ModelAndView modelAndView = new ModelAndView();
        List<News> news = newsRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        modelAndView.addObject("news", news);
        modelAndView.setViewName("blog");
        return modelAndView;
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

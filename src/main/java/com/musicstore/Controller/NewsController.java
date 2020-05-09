package com.musicstore.Controller;

import com.musicstore.Model.News;
import com.musicstore.Repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

public class NewsController {
    @Autowired
    private NewsRepository newsRepository;

    @RequestMapping(value = "/admin/news", method = RequestMethod.POST)
    public ModelAndView addNews(@Valid News news, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        newsRepository.save(news);
        modelAndView.addObject("successMessage", "News Added Successfully");
        modelAndView.setViewName("admin/newsAdd");

        return modelAndView;
    }

    @RequestMapping(value="/admin/newstList", method = RequestMethod.GET)
    public ModelAndView newsList(){
        ModelAndView modelAndView = new ModelAndView();
        List<News> news = newsRepository.findAll();
        modelAndView.addObject("newslist", news);
        modelAndView.setViewName("admin/news");
        return modelAndView;
    }

    @RequestMapping(value="/admin/news", method = RequestMethod.GET)
    public ModelAndView contactList(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/newsAdd");
        return modelAndView;
    }
}

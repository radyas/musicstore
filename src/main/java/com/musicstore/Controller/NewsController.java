package com.musicstore.Controller;

import com.musicstore.Model.News;
import com.musicstore.Model.User;
import com.musicstore.Repository.NewsRepository;
import com.musicstore.Service.NewsService;
import com.musicstore.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class NewsController {
    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private NewsService newsService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/admin/news/{id}", method = RequestMethod.GET)
    public ModelAndView deleteNews(@PathVariable("id") long id){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User person = userService.findUserByUserName(auth.getName());
        newsRepository.deleteById(id);
        modelAndView.addObject("successMessage", "News has been deleted successfully");
        List<News> news = newsRepository.findAll();
        modelAndView.addObject("user", person);
        modelAndView.addObject("newslist", news);
        modelAndView.setViewName("admin/news");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/news/{id}", method = RequestMethod.POST)
    public ModelAndView addNews(@PathVariable("id") long id, @Valid News news, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        News old = newsService.findNewsById(id);
        if (news.getName() != ""){
            old.setName(news.getName());
        }
        if (news.getDescription() != ""){
            old.setDescription(news.getDescription());
        }
        newsRepository.save(old);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        List<News> newsList = newsRepository.findAll();
        modelAndView.addObject("newslist", newsList);
        modelAndView.addObject("user", user);
        modelAndView.addObject("successMessage", "News Updated Successfully");
        modelAndView.setViewName("admin/news");

        return modelAndView;
    }

    @RequestMapping(value = "/admin/news", method = RequestMethod.POST)
    public ModelAndView addNews(@Valid News news, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        news.setCreatedBy(auth.getName());
        newsRepository.save(news);
        User user = userService.findUserByUserName(auth.getName());
        modelAndView.addObject("user", user);
        modelAndView.addObject("successMessage", "News Added Successfully");
        modelAndView.setViewName("admin/newsAdd");

        return modelAndView;
    }

    @RequestMapping(value="/admin/newsList", method = RequestMethod.GET)
    public ModelAndView newsList(){
        ModelAndView modelAndView = new ModelAndView();
        List<News> news = newsRepository.findAll();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        modelAndView.addObject("user", user);
        modelAndView.addObject("newslist", news);
        modelAndView.setViewName("admin/news");
        return modelAndView;
    }

    @RequestMapping(value="/admin/news", method = RequestMethod.GET)
    public ModelAndView newsForm(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        modelAndView.addObject("user", user);
        modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
        modelAndView.setViewName("admin/newsAdd");
        return modelAndView;
    }
}

package com.musicstore.Controller;

import com.musicstore.Model.Artist;
import com.musicstore.Model.User;
import com.musicstore.Repository.ArtistRepository;
import com.musicstore.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ArtistController {

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/admin/artist", method = RequestMethod.POST)
    public ModelAndView addArtist(@Valid Artist artist, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        artistRepository.save(artist);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        modelAndView.addObject("user", user);
        modelAndView.addObject("successMessage", "News Added Successfully");
        modelAndView.setViewName("admin/artistAdd");

        return modelAndView;
    }

    @RequestMapping(value="/admin/artistList", method = RequestMethod.GET)
    public ModelAndView artistList(){
        ModelAndView modelAndView = new ModelAndView();
        List<Artist> artist = artistRepository.findAll();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        modelAndView.addObject("user", user);
        modelAndView.addObject("artistList", artist);
        modelAndView.setViewName("admin/artist");
        return modelAndView;
    }

    @RequestMapping(value="/admin/artist", method = RequestMethod.GET)
    public ModelAndView artistAdd(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        modelAndView.addObject("user", user);
        modelAndView.setViewName("admin/artistAdd");
        return modelAndView;
    }
}

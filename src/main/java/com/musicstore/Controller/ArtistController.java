package com.musicstore.Controller;

import com.musicstore.Model.Artist;
import com.musicstore.Model.User;
import com.musicstore.Repository.ArtistRepository;
import com.musicstore.Service.ArtistService;
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
public class ArtistController {

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ArtistService artistService;

    @RequestMapping(value = "/admin/artist/{id}", method = RequestMethod.GET)
    public ModelAndView deleteNews(@PathVariable("id") long id){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User person = userService.findUserByUserName(auth.getName());
        artistRepository.deleteById(id);
        modelAndView.addObject("successMessage", "Artist has been deleted successfully");
        List<Artist> artists = artistRepository.findAll();
        modelAndView.addObject("user", person);
        modelAndView.addObject("artists", artists);
        modelAndView.setViewName("admin/artist");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/artist/{id}", method = RequestMethod.POST)
    public ModelAndView addNews(@PathVariable("id") long id, @Valid Artist artist, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        Artist old = artistService.findArtistById(id);
        if (artist.getName() != ""){
            old.setName(artist.getName());
        }
        if (artist.getBio() != ""){
            old.setBio(artist.getBio());
        }
        artistRepository.save(old);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        List<Artist> artists = artistRepository.findAll();
        modelAndView.addObject("artists", artists);
        modelAndView.addObject("user", user);
        modelAndView.addObject("successMessage", "Artist Updated Successfully");
        modelAndView.setViewName("admin/artist");

        return modelAndView;
    }

    @RequestMapping(value = "/admin/artist", method = RequestMethod.POST)
    public ModelAndView addArtist(@Valid Artist artist, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        artistRepository.save(artist);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        modelAndView.addObject("user", user);
        modelAndView.addObject("successMessage", "Artist Added Successfully");
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

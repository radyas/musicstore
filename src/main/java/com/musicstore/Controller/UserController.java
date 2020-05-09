package com.musicstore.Controller;

import com.musicstore.Model.User;
import com.musicstore.Repository.UserRepository;
import com.musicstore.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView profile(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        modelAndView.addObject("user", user);
        modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
        modelAndView.setViewName("admin/profile");
        return modelAndView;
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public ModelAndView editProfile(@Valid User user, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User person = userService.findUserByUserName(auth.getName());
        if (person != null) {
            if (user.getPassword().equals(user.getConfirm())){
                if (user.getPassword() != ""){
                    person.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
                }
                person.setEmail(user.getEmail());
                person.setFirstName(user.getFirstName());
                person.setLastName(user.getLastName());
                userService.updateUser(person);
                modelAndView.addObject("successMessage", "User has been updated successfully");
            }
            else{
                bindingResult
                    .rejectValue("password", "error.user",
                            "Passwords do not match");

            }
        }
        modelAndView.addObject("user", person);
        modelAndView.setViewName("admin/profile");
        return modelAndView;
    }
}

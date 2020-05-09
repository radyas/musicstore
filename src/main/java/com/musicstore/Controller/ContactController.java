package com.musicstore.Controller;

import com.musicstore.Model.Contact;
import com.musicstore.Model.User;
import com.musicstore.Repository.ContactRepository;
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
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/contact", method = RequestMethod.POST)
    public ModelAndView newContact(@Valid Contact contact, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        contactRepository.save(contact);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        modelAndView.addObject("user", user);
        modelAndView.addObject("successMessage", "Message Sent Successfully");
        modelAndView.setViewName("contactus");

        return modelAndView;
    }

    @RequestMapping(value="/admin/contactList", method = RequestMethod.GET)
    public ModelAndView contactList(){
        ModelAndView modelAndView = new ModelAndView();
        List<Contact> contact = contactRepository.findAll();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        modelAndView.addObject("user", user);
        modelAndView.addObject("contacts", contact);
        modelAndView.setViewName("admin/contact");
        return modelAndView;
    }
}

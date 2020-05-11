package com.musicstore.Controller;

import com.musicstore.Model.Contact;
import com.musicstore.Model.User;
import com.musicstore.Repository.ContactRepository;
import com.musicstore.Service.ContactService;
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
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ContactService contactService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/admin/contact/{id}", method = RequestMethod.POST)
    public ModelAndView deleteContact(@PathVariable("id") long id){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User person = userService.findUserByUserName(auth.getName());
        contactRepository.deleteById(id);
        modelAndView.addObject("successMessage", "News has been deleted successfully");
        List<Contact> contacts = contactRepository.findAll();
        modelAndView.addObject("user", person);
        modelAndView.addObject("contacts", contacts);
        modelAndView.setViewName("admin/contact");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/contact/{id}", method = RequestMethod.GET)
    public ModelAndView editContact(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView();
        Contact old = contactService.findByContactId(id);
        old.setStatus("Read");
        contactRepository.save(old);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        List<Contact> contacts = contactRepository.findAll();
        modelAndView.addObject("contacts", contacts);
        modelAndView.addObject("user", user);
        modelAndView.addObject("successMessage", "Message Marked as Read");
        modelAndView.setViewName("admin/contact");

        return modelAndView;
    }

    @RequestMapping(value = "/contact", method = RequestMethod.POST)
    public ModelAndView newContact(@Valid Contact contact, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        contact.setStatus("New");
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

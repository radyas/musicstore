package com.musicstore.Controller;

import com.musicstore.Model.Contact;
import com.musicstore.Repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value = "/contact", method = RequestMethod.POST)
    public ModelAndView newContact(@Valid Contact contact, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        contactRepository.save(contact);
        modelAndView.addObject("successMessage", "Message Sent Successfully");
        modelAndView.setViewName("contactus");

        return modelAndView;
    }

    @RequestMapping(value="/admin/contactList", method = RequestMethod.GET)
    public ModelAndView contactList(){
        ModelAndView modelAndView = new ModelAndView();
        List<Contact> contact = contactRepository.findAll();
        modelAndView.addObject("contacts", contact);
        modelAndView.setViewName("admin/contact");
        return modelAndView;
    }
}

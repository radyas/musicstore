package com.musicstore.Service;

import com.musicstore.Model.Contact;
import com.musicstore.Repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    public Contact findByContactId(Long id){
        return contactRepository.findContactById(id);
    }
}

package com.musicstore.Service;

import com.musicstore.Model.User;
import com.musicstore.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserService {

    @Autowired
    private UserRepository userRepo;

    public List<User> list(){
        return userRepo.findAll();
    }
}

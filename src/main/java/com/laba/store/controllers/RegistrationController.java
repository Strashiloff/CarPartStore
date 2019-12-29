package com.laba.store.controllers;

import com.laba.store.domain.User;
import com.laba.store.repos.PostRepo;
import com.laba.store.repos.UserRepo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    private final UserRepo userRepo;

    @Autowired
    private PostRepo postRepo;

    @Autowired
    public RegistrationController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @PostMapping
    public User registration(@RequestBody User user){
        User userFromDb = userRepo.findByUsername(user.getUsername());
        if(userFromDb != null){
            user.setId(-1L);
            return user;
        }
        user.setAction(true);
        return userRepo.save(user);
    }
}

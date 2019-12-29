package com.laba.store.controllers;

import com.laba.store.domain.User;
import com.laba.store.repos.PostRepo;
import com.laba.store.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public User registration(@RequestBody User user){
        User userFromDb = userRepo.findByUsername(user.getUsername());
        if(userFromDb != null){
            user.setId(-1L);
            return user;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAction(true);
        return userRepo.save(user);
    }
}

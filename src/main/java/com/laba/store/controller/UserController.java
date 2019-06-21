package com.laba.store.controller;

import com.laba.store.domain.User;
import com.laba.store.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private final UserRepo userRepo;

    public UserController(UserRepo userRepo) { this.userRepo = userRepo; }

    @GetMapping
    public List<User> getAllUser(){
        return userRepo.findAll();
    }

    @GetMapping("current")
    public User getCurrent(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepo.findByUsername(authentication.getName());
        user.setPassword("");
        return user;
    }
}

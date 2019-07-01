package com.laba.store.controller;

import com.laba.store.domain.User;
import com.laba.store.repos.UserRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

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

    @PutMapping("{id}")
    public void Update(@PathVariable("id") User userFromDb, @RequestBody User user){
        BeanUtils.copyProperties(user, userFromDb, "id");
        userRepo.save(userFromDb);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable("id") User user){
        userRepo.delete(user);
    }
}

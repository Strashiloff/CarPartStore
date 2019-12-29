package com.laba.store.controllers;

import com.laba.store.domain.User;
import com.laba.store.services.DataBaseService;
import com.laba.store.repos.UserRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController  {

    private final UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DataBaseService dataBaseService;

    public UserController(UserRepo userRepo) { this.userRepo = userRepo; }

    @GetMapping
    public List<User> getAllUser(){ return userRepo.findAll(); }

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
        userFromDb.setPassword(passwordEncoder.encode(userFromDb.getPassword()));
        userRepo.save(userFromDb);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable("id") User user){
        userRepo.delete(user);
    }
}

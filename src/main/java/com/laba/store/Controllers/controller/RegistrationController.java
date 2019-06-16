package com.laba.store.Controllers.controller;

import com.laba.store.Controllers.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {
    @GetMapping("/registration")
    public String test(){
        return "index";
    }

    @PostMapping("/registration")
    public void registration(@RequestBody User user){
        User newUser = user;
    }
}

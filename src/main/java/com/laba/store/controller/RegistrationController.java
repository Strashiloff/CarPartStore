package com.laba.store.controller;

import com.laba.store.domain.Post;
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
    public String registration(@RequestBody User user){
        User userFromDb = userRepo.findByUsername(user.getUsername());
        JSONObject object = new JSONObject();
        if(userFromDb !=null){
            object.put("error", "User \"" + user.getUsername() + "\" is exist!");
            object.put("check", true);
            return object.toString();
        }
        user.setAction(true);
        object.put("error", "");
        object.put("check", false);
        Post post = new Post();
        post.setPost("Chief Administrator");
        postRepo.save(post);
        user.setPosition(post);
        userRepo.save(user);
        return object.toString();
    }
}

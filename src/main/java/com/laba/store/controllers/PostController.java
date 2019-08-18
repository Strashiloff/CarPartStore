package com.laba.store.controllers;

import com.laba.store.domain.Post;
import com.laba.store.repos.PostRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("post")
public class PostController {

    private PostRepo postRepo;

    public PostController(PostRepo postRepo) {
        this.postRepo = postRepo;
    }

    @GetMapping
    public List<Post> getAllPost() { return postRepo.findAll(); }

    @PostMapping
    public Post newPost(@RequestBody Post post){
        Post postFromDb = postRepo.findByPost(post.getPost());
        if(postFromDb!=null) return null;
        return postRepo.save(post);
    }

    @DeleteMapping("{id}")
    public void deletePost(@PathVariable("id") Post post){
        postRepo.delete(post);
    }
}

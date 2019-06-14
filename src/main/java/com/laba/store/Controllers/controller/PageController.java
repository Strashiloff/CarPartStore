package com.laba.store.Controllers.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PageController {

    @GetMapping
    public String greeting(){
        return "index.html";
    }

    @GetMapping("/test")
    public String test(){return "test.html";}
}

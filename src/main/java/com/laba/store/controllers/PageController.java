package com.laba.store.controllers;

import com.laba.store.domain.User;
import com.laba.store.repos.UserRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Controller
@RequestMapping("/")
public class PageController {

    @Value("${spring.profiles.active:prod}")
    private String profile;

    private final UserRepo userRepo;

    public PageController(UserRepo userRepo) { this.userRepo = userRepo; }

    @GetMapping
    public String greeting(Model model){
        HashMap<Object, Object> data = new HashMap<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userRepo.findByUsername(userName);
        //data.put("profile", user.getName()+ " " + user.getSurname());
        model.addAttribute("frontendData", data);
        model.addAttribute("idDevMode", "dev".equals(profile));
        return "index";
    }
    @GetMapping("/logout")
    public String userLogout(HttpServletRequest request, HttpServletResponse response){
         Authentication auth = SecurityContextHolder.getContext().getAuthentication();
         if(auth != null){
             new SecurityContextLogoutHandler().logout(request, response, auth);
         }
        return "redirect:/login?logout";
    }
}

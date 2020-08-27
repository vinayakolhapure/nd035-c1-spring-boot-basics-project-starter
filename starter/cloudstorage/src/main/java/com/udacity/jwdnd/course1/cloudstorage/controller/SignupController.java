package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignupController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/signup")
    public String signUp() {
        return "signup";
    }

    @PostMapping(value = "/register")
    public String register(@ModelAttribute("SpringWeb") User user) {
        if (user == null) {
            return "redirect:signup";
        }

        try {
            userService.register(user);
        } catch (Exception e) {
            return "redirect:signup?error";
        }

        return "redirect:signup?success";
    }
}

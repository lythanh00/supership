package com.supership.ship.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/")
public class LoginAPI {
    @GetMapping
    public String showHomePage(){
        return "index";
    }

    @GetMapping("login")
    public String showLogin(){
        return "login";
    }

    @GetMapping("register")
    public String showRegister(){
        return "register";
    }
}

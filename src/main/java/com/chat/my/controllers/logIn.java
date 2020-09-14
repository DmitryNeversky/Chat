package com.chat.my.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class logIn {

    @GetMapping("auth")
    public String auth(){
        return "auth";
    }

    @GetMapping("home")
    public String home(){
        return "home";
    }

    @GetMapping("reg")
    public String reg(){
        return "reg";
    }
}
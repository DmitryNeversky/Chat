package com.chat.my.controllers;

import com.chat.my.entities.User;
import com.chat.my.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LogIn {

    private final UserRepository userRepository;

    public LogIn(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password){
        User findUser = userRepository.findByName(username);

        if(findUser == null){
            System.out.println("NULL");

            // Загрузка аватарки и присваивание пути в конструктор

            User user = new User(username, password, "avatarPath");
            userRepository.save(user);
        } else {
            System.out.println("NOTNULL");
        }

        System.out.println(username + ' ' + password);
        return "/auth";
    }

    @GetMapping("auth")
    public String auth(){
        return "auth";
    }

    @GetMapping("reg")
    public String reg(){
        return "reg";
    }
}
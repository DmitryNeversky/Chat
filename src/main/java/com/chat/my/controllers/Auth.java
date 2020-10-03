package com.chat.my.controllers;

import com.chat.my.entities.User;
import com.chat.my.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
public class Auth {

    @Value("${upload.path}")
    private String uploadPath;

    private final UserRepository userRepository;

    public Auth(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public void onLogin(@RequestParam String username){

    }

    @PostMapping("/registration")
    public void onRegistration(String username, String password, MultipartFile avatar, Model model){
        User findUser = userRepository.findByName(username);

        if(findUser == null){
            String avatarPath = "/static/img/default.png";

            if (avatar.getSize() != 0) {
                System.out.println(uploadPath);

                avatarPath = uploadPath + "/" + UUID.randomUUID() + avatar.getOriginalFilename();

                try {
                    avatar.transferTo(new File(avatarPath));
                } catch (IOException e){
                    e.printStackTrace();
                }
            }

            User user = new User(username, password, avatarPath);
            userRepository.save(user);
        } else {
            model.addAttribute("error", "Пользователь с таким именем уже существует!");
        }
    }

    @GetMapping("/login")
    public String getLogin(){
        return "login";
    }

    @GetMapping("/registration")
    public String getRegistration(){
        return "registration";
    }
}
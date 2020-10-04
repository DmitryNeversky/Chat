package com.chat.my.controllers;

import com.chat.my.entities.User;
import com.chat.my.repositories.UserRepository;
import com.chat.my.services.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class Auth {

    private final UserRepository userRepository;
    private final FileService fileService;

    public Auth(UserRepository userRepository, FileService fileService) {
        this.userRepository = userRepository;
        this.fileService = fileService;
    }

    @PostMapping("/login")
    public void onLogin(@RequestParam String username){

    }

    @PostMapping("/registration")
    public void onRegistration(String username, String password, @RequestParam("avatar") MultipartFile avatar, Model model){
        User findUser = userRepository.findByName(username);

        if(findUser == null){
            String avatarPath = "/static/img/default.png";

            if (avatar.getSize() != 0) avatarPath = "uploads" + '/' + fileService.uploadFile(avatar);

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
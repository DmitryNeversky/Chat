package com.chat.my.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
public class FileService {

    @Value("${upload.path}")
    private String uploadPath;

    private String fileName;

    public String uploadFile(MultipartFile file){
        try{
            fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + '/' + fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileName;
    }
}

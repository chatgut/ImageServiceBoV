package com.example.controller;


import com.example.entity.ImageEntity;
import com.example.repository.ImageRepository;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;



@RestController
@RequestMapping("/images")
public class ImageController {

    private final ImageRepository imageRepository;

    public ImageController(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }


    @PostMapping()
    ImageEntity postImage(@RequestParam("image") MultipartFile file) throws IOException {

        ImageEntity imageEntity = new ImageEntity();

        imageEntity.setContentType(file.getContentType());
        imageEntity.setPicByte(file.getBytes());
        imageEntity.setName(file.getName() + Math.random());
        imageRepository.save(imageEntity);
        // todo: save to database
        return imageEntity;

    }
    // todo: @GetMapping





}

package com.example.controller;

import com.example.entity.ImageEntity;
import com.example.repository.ImageRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;



@RestController
@RequestMapping("/images")
public class ImageController {

    private final ImageRepository imageRepository;

    public ImageController(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }


    @PostMapping()
    ImageEntity createImage(@RequestParam("image") MultipartFile file){

        try {
        ImageEntity imageEntity = new ImageEntity();

        imageEntity.setContentType(file.getContentType());
        imageEntity.setPicByte(file.getBytes());
        imageEntity.setName(file.getName() + Math.random());
        imageRepository.save(imageEntity);
        // todo: save to database
        return imageEntity;
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/{id}")
    ImageEntity readImage(@PathVariable Long id) {
        return imageRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }









}

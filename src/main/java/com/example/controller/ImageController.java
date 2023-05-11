package com.example.controller;

import com.example.entity.ImageEntity;
import com.example.repository.ImageRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    ImageEntity createImage(@RequestParam("image") MultipartFile file) {

        try {
            ImageEntity imageEntity = new ImageEntity();

            imageEntity.setContentType(file.getContentType());
            imageEntity.setPicByte(file.getBytes());
            imageEntity.setName(file.getName() + Math.random());
            imageRepository.save(imageEntity);
            return imageEntity;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/{id}")
    ResponseEntity<byte[]> readImage(@PathVariable long id) {

        try {
            var image = imageRepository.findById(id);
            HttpHeaders headers = new HttpHeaders();
            if (image.isPresent()) {
                headers.add("Content-Type", image.get().getContentType());

                return ResponseEntity
                        .status(HttpStatus.OK)
                        .headers(headers)
                        .body(image.get().getPicByte());
            } else
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            } catch(Exception e){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        }


}

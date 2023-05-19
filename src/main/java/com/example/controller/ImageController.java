package com.example.controller;

import com.example.entity.ImageEntity;
import com.example.repository.ImageRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;


@RestController
@RequestMapping("/images")
@CrossOrigin
public class ImageController {

    private final ImageRepository imageRepository;

    public ImageController(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }


    @PostMapping()
    String createImage(@RequestParam("image") MultipartFile file, UriComponentsBuilder ur) {

        try {
            ImageEntity imageEntity = new ImageEntity();

            imageEntity.setContentType(file.getContentType());
            imageEntity.setPicByte(file.getBytes());
            imageEntity.setName(file.getName() + UUID.randomUUID());
            imageRepository.save(imageEntity);
            return ur.path("/images/" + imageEntity.getId()).toUriString();
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
    @DeleteMapping("/{id}")
    void deleteImage (@PathVariable long id){
        imageRepository.deleteById(id);
    }
}

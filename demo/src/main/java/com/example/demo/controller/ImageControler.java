package com.example.demo.controller;

import com.example.demo.entity.Image;
import com.example.demo.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/image")
public class ImageControler {

    @Autowired
    private ImageService imageService;

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> get(@PathVariable("id") Long id) {
        Image image = imageService.readImage(id);
        return ResponseEntity.ok().contentType(MediaType.valueOf(image.getTyp())).body(image.getBytes());
    }

    @PostMapping
    public ResponseEntity<Image> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
        return new ResponseEntity<Image>(imageService.createImage(file), HttpStatus.CREATED);
    }
}

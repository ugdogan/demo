package com.example.demo.service;

import com.example.demo.entity.Image;
import com.example.demo.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public Image createImage(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setTyp(file.getContentType());
        image.setBytes(file.getBytes());
        return imageRepository.save(image);
    }

    public Image readImage(Long id) {
        return imageRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id.toString()));
    }
}

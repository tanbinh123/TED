package com.uoa.AirBnB.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.uoa.AirBnB.model.imageModel.ImageDto;
import com.uoa.AirBnB.service.ImageService;
import com.uoa.AirBnB.util.Helpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/images")
public class ImageController {

    @Autowired
    ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
        ImageDto img = new ImageDto(file.getOriginalFilename(), file.getContentType(), file.getBytes());
        img = imageService.uploadImage(img);
        return ResponseEntity.ok().body(Helpers.convertToJson(img));
    }

    @GetMapping("/{imageName}")
    public ResponseEntity<String> getImage(@PathVariable("imageName") String imageName) throws Exception {
        ImageDto retrievedImage = imageService.findByName(imageName);
        return ResponseEntity.ok().body(Helpers.convertToJson(retrievedImage));
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateImageInformation(@RequestBody ImageDto imageDto) throws JsonProcessingException {
        imageDto = imageService.uploadImage(imageDto);
        return ResponseEntity.ok().body(Helpers.convertToJson(imageDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteImage(@PathVariable("id") Long id){
        imageService.deleteById(id);

        return ResponseEntity.ok().body("{\"Status\": \"Successful Deletion\"}");
    }



}
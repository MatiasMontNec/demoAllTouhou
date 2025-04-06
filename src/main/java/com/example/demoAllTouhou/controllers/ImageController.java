package com.example.demoAllTouhou.controllers;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    private final Path rootLocation = Paths.get("src/main/resources/static");

    @GetMapping("/characters/{characterName}/{imageName}")
    public ResponseEntity<Resource> getCharacterImage(
            @PathVariable String characterName,
            @PathVariable String imageName) {

        try {
            Path file = rootLocation.resolve("images/characters/" + characterName + "/" + imageName);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() && resource.isReadable()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_PNG) // Ajusta según el tipo de imagen
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
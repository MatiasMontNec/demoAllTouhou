package com.example.demoAllTouhou.services;

import com.example.demoAllTouhou.entities.ImagesEntity;
import com.example.demoAllTouhou.repositories.ImagesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImagesService {

    private final ImagesRepository imagesRepository;

    public ImagesService(ImagesRepository imagesRepository) {
        this.imagesRepository = imagesRepository;
    }

    // **Crear una nueva imagen**
    public ImagesEntity createImage(ImagesEntity imagesEntity) {
        return imagesRepository.save(imagesEntity);
    }

    // **Obtener todas las imágenes**
    public List<ImagesEntity> getAllImages() {
        return imagesRepository.findAll();
    }

    // **Obtener una imagen por ID**
    public ImagesEntity getImageById(Long id) {
        return imagesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Image not found with id: " + id));
    }

    // **Actualizar una imagen**
    public ImagesEntity updateImage(Long id, ImagesEntity updatedImage) {
        ImagesEntity existingImage = imagesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Image not found with id: " + id));

        if (updatedImage.getName() != null && !updatedImage.getName().isEmpty()) existingImage.setName(updatedImage.getName());
        if (updatedImage.getDescription() != null && !updatedImage.getDescription().isEmpty()) existingImage.setDescription(updatedImage.getDescription());
        if (updatedImage.getImage() != null) existingImage.setImage(updatedImage.getImage());

        return imagesRepository.save(existingImage);
    }

    // **Eliminar una imagen por ID**
    public void deleteImage(Long id) {
        if (!imagesRepository.existsById(id)) {
            throw new RuntimeException("Image not found with id: " + id);
        }
        imagesRepository.deleteById(id);
    }

    // **Buscar imágenes por parte del nombre**
    public List<ImagesEntity> getImagesByNameContains(String name) {
        return imagesRepository.findByNameContainingIgnoreCase(name);
    }
}

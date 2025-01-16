package com.example.demoAllTouhou.services;

import com.example.demoAllTouhou.entities.ImagesEntity;
import com.example.demoAllTouhou.repositories.ImagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImagesService {

    @Autowired
    private ImagesRepository imagesRepository;

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

        existingImage.setName(updatedImage.getName());
        existingImage.setDescription(updatedImage.getDescription());
        existingImage.setCharacterId(updatedImage.getCharacterId());
        existingImage.setGameId(updatedImage.getGameId());
        existingImage.setMercancyId(updatedImage.getMercancyId());

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

    // **Obtener imágenes por characterId**
    public List<ImagesEntity> getImagesByCharacterId(Long characterId) {
        return imagesRepository.findByCharacterId(characterId);
    }

    // **Obtener imágenes por gameId**
    public List<ImagesEntity> getImagesByGameId(Long gameId) {
        return imagesRepository.findByGameId(gameId);
    }

    // **Obtener imágenes por mercancyId**
    public List<ImagesEntity> getImagesByMercancyId(Long mercancyId) {
        return imagesRepository.findByMercancyId(mercancyId);
    }
}

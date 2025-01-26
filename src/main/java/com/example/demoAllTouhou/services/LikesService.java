package com.example.demoAllTouhou.services;

import com.example.demoAllTouhou.entities.LikesEntity;
import com.example.demoAllTouhou.repositories.LikesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikesService {

    private final LikesRepository likesRepository;

    public LikesService(LikesRepository likesRepository) {
        this.likesRepository = likesRepository;
    }

    // **Crear un nuevo like**
    public LikesEntity createLike(LikesEntity likesEntity) {
        return likesRepository.save(likesEntity);
    }

    // **Obtener todos los likes**
    public List<LikesEntity> getAllLikes() {
        return likesRepository.findAll();
    }

    // **Obtener un like por ID**
    public LikesEntity getLikeById(Long id) {
        return likesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Like not found with id: " + id));
    }

    // **Actualizar un like**
    public LikesEntity updateLike(Long id, LikesEntity updatedLike) {
        LikesEntity existingLike = likesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Like not found with id: " + id));

        if (updatedLike.getName() != null && !updatedLike.getName().isEmpty()) existingLike.setName(updatedLike.getName());
        if (updatedLike.getDescription() != null && !updatedLike.getDescription().isEmpty())existingLike.setDescription(updatedLike.getDescription());

        return likesRepository.save(existingLike);
    }

    // **Eliminar un like por ID**
    public void deleteLike(Long id) {
        if (!likesRepository.existsById(id)) {
            throw new RuntimeException("Like not found with id: " + id);
        }
        likesRepository.deleteById(id);
    }

    // **Buscar likes por parte del nombre**
    public List<LikesEntity> getLikesByNameContains(String name) {
        return likesRepository.findByNameContainingIgnoreCase(name);
    }

    // **Obtener likes por characterId**
    public List<LikesEntity> getLikesByCharacterId(Long characterId) {
        return likesRepository.findByCharacters_Id(characterId);
    }
}

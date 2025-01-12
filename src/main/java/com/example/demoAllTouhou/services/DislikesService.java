package com.example.demoAllTouhou.services;

import com.example.demoAllTouhou.entities.DislikesEntity;
import com.example.demoAllTouhou.repositories.DislikesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DislikesService {

    @Autowired
    private DislikesRepository dislikesRepository;

    // **Crear un nuevo Dislike**
    public DislikesEntity createDislike(DislikesEntity dislikesEntity) {
        return dislikesRepository.save(dislikesEntity);
    }

    // **Obtener todos los Dislikes**
    public List<DislikesEntity> getAllDislikes() {
        return dislikesRepository.findAll();
    }

    // **Obtener un Dislike por ID**
    public DislikesEntity getDislikeById(Long id) {
        return dislikesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dislike not found with id: " + id));
    }

    // **Actualizar un Dislike**
    public DislikesEntity updateDislike(Long id, DislikesEntity updatedDislike) {
        DislikesEntity existingDislike = dislikesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dislike not found with id: " + id));

        existingDislike.setName(updatedDislike.getName());
        existingDislike.setDescription(updatedDislike.getDescription());
        existingDislike.setCharacterId(updatedDislike.getCharacterId());

        return dislikesRepository.save(existingDislike);
    }

    // **Eliminar un Dislike por ID**
    public void deleteDislike(Long id) {
        if (!dislikesRepository.existsById(id)) {
            throw new RuntimeException("Dislike not found with id: " + id);
        }
        dislikesRepository.deleteById(id);
    }

    // **Obtener todos los Dislikes asociados a un Character ID**
    public List<DislikesEntity> getDislikesByCharacterId(Long characterId) {
        return dislikesRepository.findByCharacterId(characterId);
    }
}

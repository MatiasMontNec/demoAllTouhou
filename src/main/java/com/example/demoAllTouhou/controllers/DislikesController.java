package com.example.demoAllTouhou.controllers;

import com.example.demoAllTouhou.entities.DislikesEntity;
import com.example.demoAllTouhou.models.DislikesDTO;
import com.example.demoAllTouhou.services.DislikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dislikes")
public class DislikesController {

    @Autowired
    private DislikesService dislikesService;

    // **Crear un nuevo Dislike**
    @PostMapping
    public ResponseEntity<DislikesDTO> createDislike(@RequestBody DislikesDTO dislikeDTO) {
        DislikesEntity entity = mapToEntity(dislikeDTO);
        DislikesEntity createdEntity = dislikesService.createDislike(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToDTO(createdEntity));
    }

    // **Obtener todos los Dislikes**
    @GetMapping
    public ResponseEntity<List<DislikesDTO>> getAllDislikes() {
        List<DislikesEntity> entities = dislikesService.getAllDislikes();
        List<DislikesDTO> dtos = entities.stream().map(this::mapToDTO).toList();
        return ResponseEntity.ok(dtos);
    }

    // **Obtener un Dislike por ID**
    @GetMapping("/{id}")
    public ResponseEntity<DislikesDTO> getDislikeById(@PathVariable Long id) {
        DislikesEntity entity = dislikesService.getDislikeById(id);
        return ResponseEntity.ok(mapToDTO(entity));
    }

    // **Actualizar un Dislike**
    @PutMapping("/{id}")
    public ResponseEntity<DislikesDTO> updateDislike(@PathVariable Long id, @RequestBody DislikesDTO dislikeDTO) {
        DislikesEntity entity = mapToEntity(dislikeDTO);
        DislikesEntity updatedEntity = dislikesService.updateDislike(id, entity);
        return ResponseEntity.ok(mapToDTO(updatedEntity));
    }

    // **Eliminar un Dislike por ID**
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDislike(@PathVariable Long id) {
        dislikesService.deleteDislike(id);
        return ResponseEntity.noContent().build();
    }

    // **Obtener todos los Dislikes asociados a un Character ID**
    @GetMapping("/by-character/{characterId}")
    public ResponseEntity<List<DislikesDTO>> getDislikesByCharacterId(@PathVariable Long characterId) {
        List<DislikesEntity> entities = dislikesService.getDislikesByCharacterId(characterId);
        List<DislikesDTO> dtos = entities.stream().map(this::mapToDTO).toList();
        return ResponseEntity.ok(dtos);
    }

    // **Mapear de DTO a Entidad**
    private DislikesEntity mapToEntity(DislikesDTO dto) {
        return new DislikesEntity(dto.getId(), dto.getName(), dto.getDescription(), dto.getCharacterId());
    }

    // **Mapear de Entidad a DTO**
    private DislikesDTO mapToDTO(DislikesEntity entity) {
        return new DislikesDTO(entity.getId(), entity.getName(), entity.getDescription(), entity.getCharacterId());
    }
}

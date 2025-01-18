package com.example.demoAllTouhou.controllers;

import com.example.demoAllTouhou.entities.ImagesEntity;
import com.example.demoAllTouhou.models.ImagesDTO;
import com.example.demoAllTouhou.models.ImagesListDTO;
import com.example.demoAllTouhou.services.ImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/images")
public class ImagesController {

    @Autowired
    private ImagesService imagesService;

    // **Crear una nueva imagen**
    @PostMapping
    public ResponseEntity<ImagesDTO> createImage(@RequestBody ImagesDTO imagesDTO) {
        ImagesEntity entity = mapToEntity(imagesDTO);
        ImagesEntity createdEntity = imagesService.createImage(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToDTO(createdEntity));
    }

    // **Obtener todas las imágenes**
    @GetMapping
    public ResponseEntity<ImagesListDTO> getAllImages() {
        List<ImagesEntity> entities = imagesService.getAllImages();
        List<ImagesDTO> dtos = entities.stream().map(this::mapToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(new ImagesListDTO(dtos));
    }

    // **Obtener una imagen por ID**
    @GetMapping("/{id}")
    public ResponseEntity<ImagesDTO> getImageById(@PathVariable Long id) {
        ImagesEntity entity = imagesService.getImageById(id);
        return ResponseEntity.ok(mapToDTO(entity));
    }

    // **Actualizar una imagen**
    @PutMapping("/{id}")
    public ResponseEntity<ImagesDTO> updateImage(@PathVariable Long id, @RequestBody ImagesDTO imagesDTO) {
        ImagesEntity entity = mapToEntity(imagesDTO);
        ImagesEntity updatedEntity = imagesService.updateImage(id, entity);
        return ResponseEntity.ok(mapToDTO(updatedEntity));
    }

    // **Eliminar una imagen por ID**
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long id) {
        imagesService.deleteImage(id);
        return ResponseEntity.noContent().build();
    }

    // **Buscar imágenes por parte del nombre**
    @GetMapping("/search")
    public ResponseEntity<ImagesListDTO> getImagesByNameContains(@RequestParam String name) {
        List<ImagesEntity> entities = imagesService.getImagesByNameContains(name);
        List<ImagesDTO> dtos = entities.stream().map(this::mapToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(new ImagesListDTO(dtos));
    }

    // **Obtener imágenes por characterId**
    @GetMapping("/by-character/{characterId}")
    public ResponseEntity<ImagesListDTO> getImagesByCharacterId(@PathVariable Long characterId) {
        List<ImagesEntity> entities = imagesService.getImagesByCharacterId(characterId);
        List<ImagesDTO> dtos = entities.stream().map(this::mapToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(new ImagesListDTO(dtos));
    }

    // **Obtener imágenes por gameId**
    @GetMapping("/by-game/{gameId}")
    public ResponseEntity<ImagesListDTO> getImagesByGameId(@PathVariable Long gameId) {
        List<ImagesEntity> entities = imagesService.getImagesByGameId(gameId);
        List<ImagesDTO> dtos = entities.stream().map(this::mapToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(new ImagesListDTO(dtos));
    }

    // **Obtener imágenes por mercancyId**
    @GetMapping("/by-mercancy/{mercancyId}")
    public ResponseEntity<ImagesListDTO> getImagesByMercancyId(@PathVariable Long mercancyId) {
        List<ImagesEntity> entities = imagesService.getImagesByMercancyId(mercancyId);
        List<ImagesDTO> dtos = entities.stream().map(this::mapToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(new ImagesListDTO(dtos));
    }

    // **Mapear de DTO a Entidad**
    private ImagesEntity mapToEntity(ImagesDTO dto) {
        ImagesEntity entity = new ImagesEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setCharacterId(dto.getCharacterId());
        entity.setGameId(dto.getGameId());
        entity.setMercancyId(dto.getMercancyId());
        return entity;
    }

    // **Mapear de Entidad a DTO**
    private ImagesDTO mapToDTO(ImagesEntity entity) {
        return new ImagesDTO(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getCharacterId(),
                entity.getGameId(),
                entity.getMercancyId()
        );
    }
}

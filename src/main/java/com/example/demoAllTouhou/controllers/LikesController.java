package com.example.demoAllTouhou.controllers;

import com.example.demoAllTouhou.entities.LikesEntity;
import com.example.demoAllTouhou.dtos.LikesDTO;
import com.example.demoAllTouhou.dtos.LikesListDTO;
import com.example.demoAllTouhou.services.LikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/likes")
public class LikesController {

//    @Autowired
//    private LikesService likesService;
//
//    // **Crear un nuevo like**
//    @PostMapping
//    public ResponseEntity<LikesDTO> createLike(@RequestBody LikesDTO likesDTO) {
//        LikesEntity entity = mapToEntity(likesDTO);
//        LikesEntity createdEntity = likesService.createLike(entity);
//        return ResponseEntity.status(HttpStatus.CREATED).body(mapToDTO(createdEntity));
//    }
//
//    // **Obtener todos los likes**
//    @GetMapping
//    public ResponseEntity<LikesListDTO> getAllLikes() {
//        List<LikesEntity> entities = likesService.getAllLikes();
//        List<LikesDTO> dtos = entities.stream().map(this::mapToDTO).collect(Collectors.toList());
//        return ResponseEntity.ok(new LikesListDTO(dtos));
//    }
//
//    // **Obtener un like por ID**
//    @GetMapping("/{id}")
//    public ResponseEntity<LikesDTO> getLikeById(@PathVariable Long id) {
//        LikesEntity entity = likesService.getLikeById(id);
//        return ResponseEntity.ok(mapToDTO(entity));
//    }
//
//    // **Actualizar un like**
//    @PutMapping("/{id}")
//    public ResponseEntity<LikesDTO> updateLike(@PathVariable Long id, @RequestBody LikesDTO likesDTO) {
//        LikesEntity entity = mapToEntity(likesDTO);
//        LikesEntity updatedEntity = likesService.updateLike(id, entity);
//        return ResponseEntity.ok(mapToDTO(updatedEntity));
//    }
//
//    // **Eliminar un like por ID**
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteLike(@PathVariable Long id) {
//        likesService.deleteLike(id);
//        return ResponseEntity.noContent().build();
//    }
//
//    // **Buscar likes por parte del nombre**
//    @GetMapping("/search")
//    public ResponseEntity<LikesListDTO> getLikesByNameContains(@RequestParam String name) {
//        List<LikesEntity> entities = likesService.getLikesByNameContains(name);
//        List<LikesDTO> dtos = entities.stream().map(this::mapToDTO).collect(Collectors.toList());
//        return ResponseEntity.ok(new LikesListDTO(dtos));
//    }
//
//    // **Obtener likes por characterId**
//    @GetMapping("/by-character/{characterId}")
//    public ResponseEntity<LikesListDTO> getLikesByCharacterId(@PathVariable Long characterId) {
//        List<LikesEntity> entities = likesService.getLikesByCharacterId(characterId);
//        List<LikesDTO> dtos = entities.stream().map(this::mapToDTO).collect(Collectors.toList());
//        return ResponseEntity.ok(new LikesListDTO(dtos));
//    }
//
//    // **Mapear de DTO a Entidad**
//    private LikesEntity mapToEntity(LikesDTO dto) {
//        LikesEntity entity = new LikesEntity();
//        entity.setId(dto.getId());
//        entity.setName(dto.getName());
//        entity.setDescription(dto.getDescription());
//        entity.setCharacterId(dto.getCharacterId());
//        return entity;
//    }
//
//    // **Mapear de Entidad a DTO**
//    private LikesDTO mapToDTO(LikesEntity entity) {
//        return new LikesDTO(
//                entity.getId(),
//                entity.getName(),
//                entity.getDescription(),
//                entity.getCharacterId()
//        );
//    }
}

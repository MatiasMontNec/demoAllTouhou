package com.example.demoAllTouhou.controllers;

import com.example.demoAllTouhou.entities.MangaCharacterEntity;
import com.example.demoAllTouhou.models.MangaCharacterDTO;
import com.example.demoAllTouhou.models.MangaCharacterListDTO;
import com.example.demoAllTouhou.services.MangaCharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/manga-characters")
public class MangaCharacterController {

    @Autowired
    private MangaCharacterService mangaCharacterService;

    // **Crear un nuevo MangaCharacter**
    @PostMapping
    public ResponseEntity<MangaCharacterDTO> createMangaCharacter(@RequestBody MangaCharacterDTO mangaCharacterDTO) {
        MangaCharacterEntity entity = mapToEntity(mangaCharacterDTO);
        MangaCharacterEntity createdEntity = mangaCharacterService.createMangaCharacter(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToDTO(createdEntity));
    }

    // **Obtener todos los MangaCharacter**
    @GetMapping
    public ResponseEntity<MangaCharacterListDTO> getAllMangaCharacters() {
        List<MangaCharacterEntity> entities = mangaCharacterService.getAllMangaCharacters();
        List<MangaCharacterDTO> dtos = entities.stream().map(this::mapToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(new MangaCharacterListDTO(dtos));
    }

    // **Obtener un MangaCharacter por ID**
    @GetMapping("/{id}")
    public ResponseEntity<MangaCharacterDTO> getMangaCharacterById(@PathVariable Long id) {
        MangaCharacterEntity entity = mangaCharacterService.getMangaCharacterById(id);
        return ResponseEntity.ok(mapToDTO(entity));
    }

    // **Actualizar un MangaCharacter**
    @PutMapping("/{id}")
    public ResponseEntity<MangaCharacterDTO> updateMangaCharacter(@PathVariable Long id, @RequestBody MangaCharacterDTO mangaCharacterDTO) {
        MangaCharacterEntity entity = mapToEntity(mangaCharacterDTO);
        MangaCharacterEntity updatedEntity = mangaCharacterService.updateMangaCharacter(id, entity);
        return ResponseEntity.ok(mapToDTO(updatedEntity));
    }

    // **Eliminar un MangaCharacter por ID**
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMangaCharacter(@PathVariable Long id) {
        mangaCharacterService.deleteMangaCharacter(id);
        return ResponseEntity.noContent().build();
    }

    // **Obtener MangaCharacter por mangaId**
    @GetMapping("/by-manga/{mangaId}")
    public ResponseEntity<MangaCharacterListDTO> getMangaCharactersByMangaId(@PathVariable Long mangaId) {
        List<MangaCharacterEntity> entities = mangaCharacterService.getMangaCharactersByMangaId(mangaId);
        List<MangaCharacterDTO> dtos = entities.stream().map(this::mapToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(new MangaCharacterListDTO(dtos));
    }

    // **Obtener MangaCharacter por characterId**
    @GetMapping("/by-character/{characterId}")
    public ResponseEntity<MangaCharacterListDTO> getMangaCharactersByCharacterId(@PathVariable Long characterId) {
        List<MangaCharacterEntity> entities = mangaCharacterService.getMangaCharactersByCharacterId(characterId);
        List<MangaCharacterDTO> dtos = entities.stream().map(this::mapToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(new MangaCharacterListDTO(dtos));
    }

    // **Mapear de DTO a Entidad**
    private MangaCharacterEntity mapToEntity(MangaCharacterDTO dto) {
        MangaCharacterEntity entity = new MangaCharacterEntity();
        entity.setId(dto.getId());
        entity.setMangaId(dto.getMangaId());
        entity.setCharacterId(dto.getCharacterId());
        return entity;
    }

    // **Mapear de Entidad a DTO**
    private MangaCharacterDTO mapToDTO(MangaCharacterEntity entity) {
        return new MangaCharacterDTO(
                entity.getId(),
                entity.getMangaId(),
                entity.getCharacterId()
        );
    }
}

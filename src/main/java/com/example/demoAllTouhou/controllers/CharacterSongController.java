package com.example.demoAllTouhou.controllers;

import com.example.demoAllTouhou.entities.CharacterSongEntity;
import com.example.demoAllTouhou.models.CharacterSongDTO;
import com.example.demoAllTouhou.models.IdListDTO;
import com.example.demoAllTouhou.services.CharacterSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/character-songs")
public class CharacterSongController {

    @Autowired
    private CharacterSongService characterSongService;

    // **Crear una nueva relación Character-Song**
    @PostMapping
    public ResponseEntity<CharacterSongDTO> createCharacterSong(@RequestBody CharacterSongDTO characterSongDTO) {
        CharacterSongEntity entity = mapToEntity(characterSongDTO);
        CharacterSongEntity createdEntity = characterSongService.createCharacterSong(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToDTO(createdEntity));
    }

    // **Obtener todas las relaciones Character-Song**
    @GetMapping
    public ResponseEntity<List<CharacterSongDTO>> getAllCharacterSongs() {
        List<CharacterSongEntity> entities = characterSongService.getAllCharacterSongs();
        List<CharacterSongDTO> dtos = entities.stream().map(this::mapToDTO).toList();
        return ResponseEntity.ok(dtos);
    }

    // **Obtener una relación Character-Song por ID**
    @GetMapping("/{id}")
    public ResponseEntity<CharacterSongDTO> getCharacterSongById(@PathVariable Long id) {
        CharacterSongEntity entity = characterSongService.getCharacterSongById(id);
        return ResponseEntity.ok(mapToDTO(entity));
    }

    // **Actualizar una relación Character-Song**
    @PutMapping("/{id}")
    public ResponseEntity<CharacterSongDTO> updateCharacterSong(@PathVariable Long id, @RequestBody CharacterSongDTO characterSongDTO) {
        CharacterSongEntity entity = mapToEntity(characterSongDTO);
        CharacterSongEntity updatedEntity = characterSongService.updateCharacterSong(id, entity);
        return ResponseEntity.ok(mapToDTO(updatedEntity));
    }

    // **Eliminar una relación Character-Song por ID**
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCharacterSong(@PathVariable Long id) {
        characterSongService.deleteCharacterSong(id);
        return ResponseEntity.noContent().build();
    }

    // **Obtener una lista de Character IDs por Song ID**
    @GetMapping("/characters-by-song/{songId}")
    public ResponseEntity<IdListDTO> getCharacterIdsBySongId(@PathVariable Long songId) {
        List<Long> characterIds = characterSongService.getCharacterIdsBySongId(songId);
        return ResponseEntity.ok(new IdListDTO(characterIds));
    }

    // **Obtener una lista de Song IDs por Character ID**
    @GetMapping("/songs-by-character/{characterId}")
    public ResponseEntity<IdListDTO> getSongIdsByCharacterId(@PathVariable Long characterId) {
        List<Long> songIds = characterSongService.getSongIdsByCharacterId(characterId);
        return ResponseEntity.ok(new IdListDTO(songIds));
    }

    // **Mapear de DTO a Entidad**
    private CharacterSongEntity mapToEntity(CharacterSongDTO dto) {
        return new CharacterSongEntity(dto.getId(), dto.getCharacterId(), dto.getSongId());
    }

    // **Mapear de Entidad a DTO**
    private CharacterSongDTO mapToDTO(CharacterSongEntity entity) {
        return new CharacterSongDTO(entity.getId(), entity.getCharacterId(), entity.getSongId());
    }
}

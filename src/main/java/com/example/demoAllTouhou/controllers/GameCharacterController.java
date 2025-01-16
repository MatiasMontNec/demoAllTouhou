package com.example.demoAllTouhou.controllers;

import com.example.demoAllTouhou.entities.GameCharacterEntity;
import com.example.demoAllTouhou.models.GameCharacterDTO;
import com.example.demoAllTouhou.services.GameCharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/game-characters")
public class GameCharacterController {

    @Autowired
    private GameCharacterService gameCharacterService;

    // **Crear un nuevo GameCharacter**
    @PostMapping
    public ResponseEntity<GameCharacterDTO> createGameCharacter(@RequestBody GameCharacterDTO gameCharacterDTO) {
        GameCharacterEntity entity = mapToEntity(gameCharacterDTO);
        GameCharacterEntity createdEntity = gameCharacterService.createGameCharacter(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToDTO(createdEntity));
    }

    // **Obtener todos los GameCharacters**
    @GetMapping
    public ResponseEntity<List<GameCharacterDTO>> getAllGameCharacters() {
        List<GameCharacterEntity> entities = gameCharacterService.getAllGameCharacters();
        List<GameCharacterDTO> dtos = entities.stream().map(this::mapToDTO).toList();
        return ResponseEntity.ok(dtos);
    }

    // **Obtener un GameCharacter por ID**
    @GetMapping("/{id}")
    public ResponseEntity<GameCharacterDTO> getGameCharacterById(@PathVariable Long id) {
        GameCharacterEntity entity = gameCharacterService.getGameCharacterById(id);
        return ResponseEntity.ok(mapToDTO(entity));
    }

    // **Actualizar un GameCharacter**
    @PutMapping("/{id}")
    public ResponseEntity<GameCharacterDTO> updateGameCharacter(@PathVariable Long id, @RequestBody GameCharacterDTO gameCharacterDTO) {
        GameCharacterEntity entity = mapToEntity(gameCharacterDTO);
        GameCharacterEntity updatedEntity = gameCharacterService.updateGameCharacter(id, entity);
        return ResponseEntity.ok(mapToDTO(updatedEntity));
    }

    // **Eliminar un GameCharacter por ID**
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGameCharacter(@PathVariable Long id) {
        gameCharacterService.deleteGameCharacter(id);
        return ResponseEntity.noContent().build();
    }

    // **Obtener todos los GameCharacters asociados a un Character ID**
    @GetMapping("/by-character/{characterId}")
    public ResponseEntity<List<GameCharacterDTO>> getGameCharactersByCharacterId(@PathVariable Long characterId) {
        List<GameCharacterEntity> entities = gameCharacterService.getGameCharactersByCharacterId(characterId);
        List<GameCharacterDTO> dtos = entities.stream().map(this::mapToDTO).toList();
        return ResponseEntity.ok(dtos);
    }

    // **Obtener todos los GameCharacters asociados a un Game ID**
    @GetMapping("/by-game/{gameId}")
    public ResponseEntity<List<GameCharacterDTO>> getGameCharactersByGameId(@PathVariable Long gameId) {
        List<GameCharacterEntity> entities = gameCharacterService.getGameCharactersByGameId(gameId);
        List<GameCharacterDTO> dtos = entities.stream().map(this::mapToDTO).toList();
        return ResponseEntity.ok(dtos);
    }

    // **Mapear de DTO a Entidad**
    private GameCharacterEntity mapToEntity(GameCharacterDTO dto) {
        return new GameCharacterEntity(dto.getId(), dto.getCharacterId(), dto.getGameId());
    }

    // **Mapear de Entidad a DTO**
    private GameCharacterDTO mapToDTO(GameCharacterEntity entity) {
        return new GameCharacterDTO(entity.getId(), entity.getCharacterId(), entity.getGameId());
    }
}

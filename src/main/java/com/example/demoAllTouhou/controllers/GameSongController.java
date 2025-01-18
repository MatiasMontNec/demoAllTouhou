package com.example.demoAllTouhou.controllers;

import com.example.demoAllTouhou.entities.GameSongEntity;
import com.example.demoAllTouhou.models.GameSongDTO;
import com.example.demoAllTouhou.models.GameSongListDTO;
import com.example.demoAllTouhou.services.GameSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/game-songs")
public class GameSongController {

    @Autowired
    private GameSongService gameSongService;

    // **Crear un nuevo GameSong**
    @PostMapping
    public ResponseEntity<GameSongDTO> createGameSong(@RequestBody GameSongDTO gameSongDTO) {
        GameSongEntity entity = mapToEntity(gameSongDTO);
        GameSongEntity createdEntity = gameSongService.createGameSong(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToDTO(createdEntity));
    }

    // **Obtener todos los GameSongs**
    @GetMapping
    public ResponseEntity<GameSongListDTO> getAllGameSongs() {
        List<GameSongEntity> entities = gameSongService.getAllGameSongs();
        List<GameSongDTO> dtos = entities.stream().map(this::mapToDTO).toList();
        return ResponseEntity.ok(new GameSongListDTO(dtos));
    }

    // **Obtener un GameSong por ID**
    @GetMapping("/{id}")
    public ResponseEntity<GameSongDTO> getGameSongById(@PathVariable Long id) {
        GameSongEntity entity = gameSongService.getGameSongById(id);
        return ResponseEntity.ok(mapToDTO(entity));
    }

    // **Actualizar un GameSong**
    @PutMapping("/{id}")
    public ResponseEntity<GameSongDTO> updateGameSong(@PathVariable Long id, @RequestBody GameSongDTO gameSongDTO) {
        GameSongEntity entity = mapToEntity(gameSongDTO);
        GameSongEntity updatedEntity = gameSongService.updateGameSong(id, entity);
        return ResponseEntity.ok(mapToDTO(updatedEntity));
    }

    // **Eliminar un GameSong por ID**
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGameSong(@PathVariable Long id) {
        gameSongService.deleteGameSong(id);
        return ResponseEntity.noContent().build();
    }

    // **Obtener todos los GameSongs por songId**
    @GetMapping("/by-song/{songId}")
    public ResponseEntity<GameSongListDTO> getGameSongsBySongId(@PathVariable Long songId) {
        List<GameSongEntity> entities = gameSongService.getGameSongsBySongId(songId);
        List<GameSongDTO> dtos = entities.stream().map(this::mapToDTO).toList();
        return ResponseEntity.ok(new GameSongListDTO(dtos));
    }

    // **Obtener todos los GameSongs por gameId**
    @GetMapping("/by-game/{gameId}")
    public ResponseEntity<GameSongListDTO> getGameSongsByGameId(@PathVariable Long gameId) {
        List<GameSongEntity> entities = gameSongService.getGameSongsByGameId(gameId);
        List<GameSongDTO> dtos = entities.stream().map(this::mapToDTO).toList();
        return ResponseEntity.ok(new GameSongListDTO(dtos));
    }

    // **Mapear de DTO a Entidad**
    private GameSongEntity mapToEntity(GameSongDTO dto) {
        GameSongEntity entity = new GameSongEntity();
        entity.setId(dto.getId());
        entity.setSongId(dto.getSongId());
        entity.setGameId(dto.getGameId());
        return entity;
    }

    // **Mapear de Entidad a DTO**
    private GameSongDTO mapToDTO(GameSongEntity entity) {
        return new GameSongDTO(
                entity.getId(),
                entity.getSongId(),
                entity.getGameId()
        );
    }
}

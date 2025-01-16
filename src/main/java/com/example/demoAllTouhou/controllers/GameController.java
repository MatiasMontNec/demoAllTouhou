package com.example.demoAllTouhou.controllers;

import com.example.demoAllTouhou.entities.GameEntity;
import com.example.demoAllTouhou.models.DateDTO;
import com.example.demoAllTouhou.models.GameDTO;
import com.example.demoAllTouhou.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {

    @Autowired
    private GameService gameService;

    // **Crear un nuevo Game**
    @PostMapping
    public ResponseEntity<GameDTO> createGame(@RequestBody GameDTO gameDTO) {
        GameEntity entity = mapToEntity(gameDTO);
        GameEntity createdEntity = gameService.createGame(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToDTO(createdEntity));
    }

    // **Obtener todos los Games**
    @GetMapping
    public ResponseEntity<List<GameDTO>> getAllGames() {
        List<GameEntity> entities = gameService.getAllGames();
        List<GameDTO> dtos = entities.stream().map(this::mapToDTO).toList();
        return ResponseEntity.ok(dtos);
    }

    // **Obtener un Game por ID**
    @GetMapping("/{id}")
    public ResponseEntity<GameDTO> getGameById(@PathVariable Long id) {
        GameEntity entity = gameService.getGameById(id);
        return ResponseEntity.ok(mapToDTO(entity));
    }

    // **Actualizar un Game**
    @PutMapping("/{id}")
    public ResponseEntity<GameDTO> updateGame(@PathVariable Long id, @RequestBody GameDTO gameDTO) {
        GameEntity entity = mapToEntity(gameDTO);
        GameEntity updatedEntity = gameService.updateGame(id, entity);
        return ResponseEntity.ok(mapToDTO(updatedEntity));
    }

    // **Eliminar un Game por ID**
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
        gameService.deleteGame(id);
        return ResponseEntity.noContent().build();
    }

    // **Obtener un Game por título**
    @GetMapping("/by-title/{title}")
    public ResponseEntity<GameDTO> getGameByTitle(@PathVariable String title) {
        GameEntity entity = gameService.getGameByTitle(title);
        return ResponseEntity.ok(mapToDTO(entity));
    }

    // **Obtener todos los Games que salieron antes de una fecha**
    @PostMapping("/before-date")
    public ResponseEntity<List<GameDTO>> getGamesBeforeDate(@RequestBody DateDTO dateDTO) {
        LocalDate date = LocalDate.of(dateDTO.getYear(), dateDTO.getMonth(), dateDTO.getDay());
        List<GameEntity> entities = gameService.getGamesBeforeDate(date);
        List<GameDTO> dtos = entities.stream().map(this::mapToDTO).toList();
        return ResponseEntity.ok(dtos);
    }

    // **Obtener todos los Games que salieron después de una fecha**
    @PostMapping("/after-date")
    public ResponseEntity<List<GameDTO>> getGamesAfterDate(@RequestBody DateDTO dateDTO) {
        LocalDate date = LocalDate.of(dateDTO.getYear(), dateDTO.getMonth(), dateDTO.getDay());
        List<GameEntity> entities = gameService.getGamesAfterDate(date);
        List<GameDTO> dtos = entities.stream().map(this::mapToDTO).toList();
        return ResponseEntity.ok(dtos);
    }

    // **Mapear de DTO a Entidad**
    private GameEntity mapToEntity(GameDTO dto) {
        GameEntity entity = new GameEntity();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setLinkDownoload(dto.getLinkDownload());
        entity.setReleaseDate(dto.getReleaseDate());
        return entity;
    }

    // **Mapear de Entidad a DTO**
    private GameDTO mapToDTO(GameEntity entity) {
        return new GameDTO(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getLinkDownoload(),
                entity.getReleaseDate()
        );
    }
}

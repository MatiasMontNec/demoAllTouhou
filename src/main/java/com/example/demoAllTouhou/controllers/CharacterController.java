package com.example.demoAllTouhou.controllers;

import com.example.demoAllTouhou.entities.CharacterEntity;
import com.example.demoAllTouhou.models.CharacterDTO;
import com.example.demoAllTouhou.services.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    // **Crear un nuevo personaje**
    @PostMapping
    public ResponseEntity<CharacterDTO> createCharacter(@RequestBody CharacterDTO characterDTO) {
        CharacterEntity characterEntity = mapToEntity(characterDTO);
        CharacterEntity createdCharacter = characterService.createCharacter(characterEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToDTO(createdCharacter));
    }

    // **Obtener todos los personajes**
    @GetMapping
    public ResponseEntity<List<CharacterDTO>> getAllCharacters() {
        List<CharacterEntity> characters = characterService.getAllCharacters();
        List<CharacterDTO> characterDTOs = characters.stream().map(this::mapToDTO).toList();
        return ResponseEntity.ok(characterDTOs);
    }

    // **Obtener un personaje por ID**
    @GetMapping("/{id}")
    public ResponseEntity<CharacterDTO> getCharacterById(@PathVariable Long id) {
        CharacterEntity character = characterService.getCharacterById(id);
        return ResponseEntity.ok(mapToDTO(character));
    }

    // **Actualizar un personaje existente**
    @PutMapping("/{id}")
    public ResponseEntity<CharacterDTO> updateCharacter(@PathVariable Long id, @RequestBody CharacterDTO characterDTO) {
        CharacterEntity updatedCharacter = characterService.updateCharacter(id, mapToEntity(characterDTO));
        return ResponseEntity.ok(mapToDTO(updatedCharacter));
    }

    // **Eliminar un personaje por ID**
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCharacter(@PathVariable Long id) {
        characterService.deleteCharacter(id);
        return ResponseEntity.noContent().build();
    }

    // **Buscar un personaje por nombre**
    @GetMapping("/search/name")
    public ResponseEntity<CharacterDTO> getCharacterByName(@RequestParam String name) {
        CharacterEntity character = characterService.getCharacterByName(name);
        return ResponseEntity.ok(mapToDTO(character));
    }

    // **Buscar personajes con edad menor o igual a la ingresada**
    @GetMapping("/search/age/max")
    public ResponseEntity<List<CharacterDTO>> getCharactersByAgeLessThanOrEqual(@RequestParam int age) {
        List<CharacterEntity> characters = characterService.getCharactersByAgeLessThanOrEqual(age);
        List<CharacterDTO> characterDTOs = characters.stream().map(this::mapToDTO).toList();
        return ResponseEntity.ok(characterDTOs);
    }

    // **Buscar personajes con edad mayor o igual a la ingresada**
    @GetMapping("/search/age/min")
    public ResponseEntity<List<CharacterDTO>> getCharactersByAgeGreaterThanOrEqual(@RequestParam int age) {
        List<CharacterEntity> characters = characterService.getCharactersByAgeGreaterThanOrEqual(age);
        List<CharacterDTO> characterDTOs = characters.stream().map(this::mapToDTO).toList();
        return ResponseEntity.ok(characterDTOs);
    }

    // **Buscar personajes con altura mayor a la ingresada**
    @GetMapping("/search/height/min")
    public ResponseEntity<List<CharacterDTO>> getCharactersByHeightGreaterThan(@RequestParam int height) {
        List<CharacterEntity> characters = characterService.getCharactersByHeightGreaterThan(height);
        List<CharacterDTO> characterDTOs = characters.stream().map(this::mapToDTO).toList();
        return ResponseEntity.ok(characterDTOs);
    }

    // **Buscar personajes con altura menor a la ingresada**
    @GetMapping("/search/height/max")
    public ResponseEntity<List<CharacterDTO>> getCharactersByHeightLessThan(@RequestParam int height) {
        List<CharacterEntity> characters = characterService.getCharactersByHeightLessThan(height);
        List<CharacterDTO> characterDTOs = characters.stream().map(this::mapToDTO).toList();
        return ResponseEntity.ok(characterDTOs);
    }

    // **Buscar personajes con peso mayor al ingresado**
    @GetMapping("/search/weight/min")
    public ResponseEntity<List<CharacterDTO>> getCharactersByWeightGreaterThan(@RequestParam int weight) {
        List<CharacterEntity> characters = characterService.getCharactersByWeightGreaterThan(weight);
        List<CharacterDTO> characterDTOs = characters.stream().map(this::mapToDTO).toList();
        return ResponseEntity.ok(characterDTOs);
    }

    // **Buscar personajes con peso menor al ingresado**
    @GetMapping("/search/weight/max")
    public ResponseEntity<List<CharacterDTO>> getCharactersByWeightLessThan(@RequestParam int weight) {
        List<CharacterEntity> characters = characterService.getCharactersByWeightLessThan(weight);
        List<CharacterDTO> characterDTOs = characters.stream().map(this::mapToDTO).toList();
        return ResponseEntity.ok(characterDTOs);
    }

    // **Buscar personajes que contienen un texto en importantFacts**
    @GetMapping("/search/important-facts")
    public ResponseEntity<List<CharacterDTO>> getCharactersByImportantFactsContaining(@RequestParam String keyword) {
        List<CharacterEntity> characters = characterService.getCharactersByImportantFactsContaining(keyword);
        List<CharacterDTO> characterDTOs = characters.stream().map(this::mapToDTO).toList();
        return ResponseEntity.ok(characterDTOs);
    }

    // **Mapear de DTO a Entidad**
    private CharacterEntity mapToEntity(CharacterDTO dto) {
        return new CharacterEntity(
                dto.getId(),
                dto.getName(),
                dto.getAge(),
                dto.getGender(),
                dto.getHeight(),
                dto.getWeight(),
                dto.getBiography(),
                dto.getRelations(),
                dto.getImportantFacts()
        );
    }

    // **Mapear de Entidad a DTO**
    private CharacterDTO mapToDTO(CharacterEntity entity) {
        return new CharacterDTO(
                entity.getId(),
                entity.getName(),
                entity.getAge(),
                entity.getGender(),
                entity.getHeight(),
                entity.getWeight(),
                entity.getBiography(),
                entity.getRelations(),
                entity.getImportantFacts()
        );
    }
}

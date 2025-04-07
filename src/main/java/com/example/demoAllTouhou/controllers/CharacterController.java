package com.example.demoAllTouhou.controllers;

import com.example.demoAllTouhou.entities.CharacterEntity;
import com.example.demoAllTouhou.services.CharacterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/characters")
public class CharacterController {
    private final CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CharacterEntity> getCharacterById(@PathVariable Long id) {
        CharacterEntity character = characterService.getCharacterById(id);
        return ResponseEntity.ok(character);
    }

    @GetMapping("/firstImage")
    public ResponseEntity<List<CharacterEntity>> getAllCharacters() {
        List<CharacterEntity> characters = characterService.getAllCharacters();
        return ResponseEntity.ok(characters);
    }

    @PostMapping("/filter")
    public List<CharacterEntity> filterCharacters(
            @RequestBody List<CharacterEntity> characters,
            @RequestParam(required = false) Integer edadMin,
            @RequestParam(required = false) Integer edadMax,
            @RequestParam(required = false) String genero,
            @RequestParam(required = false) String especie,
            @RequestParam(required = false) Integer alturaMin,
            @RequestParam(required = false) Integer alturaMax,
            @RequestParam(required = false) Integer pesoMin,
            @RequestParam(required = false) Integer pesoMax
    ) {
        return characterService.filterCharacters(
                characters, edadMin, edadMax, genero, especie, alturaMin, alturaMax, pesoMin, pesoMax
        );
    }

    @GetMapping("/random")
    public ResponseEntity<CharacterEntity> getRandomCharacter() {
        try {
            CharacterEntity character = characterService.getRandomCharacter();
            return ResponseEntity.ok(character);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null); // O puedes devolver un CharacterEntity vacío
        }
    }

}








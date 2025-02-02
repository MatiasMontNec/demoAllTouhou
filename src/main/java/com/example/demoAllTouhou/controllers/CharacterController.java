package com.example.demoAllTouhou.controllers;

import com.example.demoAllTouhou.dto.CharacterDTO;
import com.example.demoAllTouhou.services.CharacterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/characters")
public class CharacterController {
    private final CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping("/firstImage")
    public ResponseEntity<List<CharacterDTO>> getAllCharacters() {
        List<CharacterDTO> characters = characterService.getAllCharactersWithSelectedAttributes();
        return ResponseEntity.ok(characters);
    }

    @PostMapping("/filter")
    public List<CharacterDTO> filterCharacters(
            @RequestBody List<CharacterDTO> characters,
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
}

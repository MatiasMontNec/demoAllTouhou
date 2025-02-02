package com.example.demoAllTouhou.controllers;

import com.example.demoAllTouhou.dto.CharacterDTO;
import com.example.demoAllTouhou.dto.GameDTO;
import com.example.demoAllTouhou.dto.SearchRequest;
import com.example.demoAllTouhou.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping("/characters")
    public List<CharacterDTO> searchCharacters(
            @RequestBody List<CharacterDTO> characters,
            @RequestParam(required = false) String query
    ) {
        return searchService.searchCharacters(characters, query);
    }

    @PostMapping("/games")
    public List<GameDTO> searchGames(
            @RequestBody List<GameDTO> games,
            @RequestParam(required = false) String query
    ) {
        return searchService.searchGames(games, query);
    }

    @PostMapping("/all")
    public List<Object> searchAll(
            @RequestBody SearchRequest request, // DTO combinado para recibir personajes y juegos
            @RequestParam(required = false) String query
    ) {
        return searchService.searchAll(request.getCharacters(), request.getGames(), query);
    }
}


package com.example.demoAllTouhou.controllers;

import com.example.demoAllTouhou.dto.GameDTO;
import com.example.demoAllTouhou.services.GameService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/firstImage")
    public List<GameDTO> getAllGames() {
        return gameService.getAllFormattedGames();
    }

    @PostMapping("/filter")
    public List<GameDTO> filterGames(
            @RequestBody List<GameDTO> games,
            @RequestParam(required = false) Integer anioMin,
            @RequestParam(required = false) Integer anioMax,
            @RequestParam(required = false) String genero
    ) {
        return gameService.filterGames(games, anioMin, anioMax, genero);
    }
}

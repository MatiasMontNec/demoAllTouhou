package com.example.demoAllTouhou.services;

import com.example.demoAllTouhou.dto.CharacterDTO;
import com.example.demoAllTouhou.dto.GameDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {

    // Método para buscar personajes por query
    public List<CharacterDTO> searchCharacters(List<CharacterDTO> characters, String query) {
        if (query == null || query.isEmpty()) {
            return characters; // Si no hay query, devolver todos los personajes
        }
        return characters.stream()
                .filter(character -> character.getNombre().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }

    // Método para buscar juegos por query
    public List<GameDTO> searchGames(List<GameDTO> games, String query) {
        if (query == null || query.isEmpty()) {
            return games; // Si no hay query, devolver todos los juegos
        }
        return games.stream()
                .filter(game -> game.getTitulo().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }

    // Método combinado que devuelve tanto personajes como juegos
    public List<Object> searchAll(List<CharacterDTO> characters, List<GameDTO> games, String query) {
        List<Object> result = new ArrayList<>();
        result.addAll(searchCharacters(characters, query));
        result.addAll(searchGames(games, query));
        return result;
    }
}
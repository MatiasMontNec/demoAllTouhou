package com.example.demoAllTouhou.dto;

import lombok.Data;

import java.util.List;

@Data
public class SearchRequest {
    private List<CharacterDTO> characters;
    private List<GameDTO> games;
}

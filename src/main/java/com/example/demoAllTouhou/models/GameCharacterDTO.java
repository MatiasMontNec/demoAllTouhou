package com.example.demoAllTouhou.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameCharacterDTO {
    private Long id;
    private Long characterId;
    private Long gameId;
}

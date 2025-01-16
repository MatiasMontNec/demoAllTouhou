package com.example.demoAllTouhou.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CharacterSongDTO {
    private Long id;
    private Long characterId;
    private Long songId;
}
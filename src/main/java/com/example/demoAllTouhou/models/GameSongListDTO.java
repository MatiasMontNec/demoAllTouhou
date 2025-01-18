package com.example.demoAllTouhou.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GameSongListDTO {
    private List<GameSongDTO> gameSongs;
}

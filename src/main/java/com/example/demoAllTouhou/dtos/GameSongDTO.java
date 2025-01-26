package com.example.demoAllTouhou.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameSongDTO {
    private Long id;
    private Long songId;
    private Long gameId;
}

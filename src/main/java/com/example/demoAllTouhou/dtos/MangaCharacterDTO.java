package com.example.demoAllTouhou.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MangaCharacterDTO {
    private Long id;
    private Long mangaId;
    private Long characterId;
}

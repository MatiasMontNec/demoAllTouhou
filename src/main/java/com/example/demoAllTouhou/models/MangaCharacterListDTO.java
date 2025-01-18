package com.example.demoAllTouhou.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MangaCharacterListDTO {
    private List<MangaCharacterDTO> mangaCharacters;
}

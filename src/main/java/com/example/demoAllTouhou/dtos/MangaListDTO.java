package com.example.demoAllTouhou.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MangaListDTO {
    private List<MangaDTO> mangas;
}

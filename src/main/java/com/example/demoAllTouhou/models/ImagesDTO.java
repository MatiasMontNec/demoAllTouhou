package com.example.demoAllTouhou.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImagesDTO {
    private Long id;
    private String name;
    private String description;
    private Long characterId;
    private Long gameId;
    private Long mercancyId;
}

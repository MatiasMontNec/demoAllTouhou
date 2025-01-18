package com.example.demoAllTouhou.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MangaDTO {
    private Long id;
    private String title;
    private String author;
    private String description;
    private String accessLink;
}

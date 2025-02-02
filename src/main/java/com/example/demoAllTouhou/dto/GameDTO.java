package com.example.demoAllTouhou.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GameDTO {
    private Long id;
    private String titulo;
    private String descripcion;
    private int año;
    private String genero;
    private String imagen;
}

package com.example.demoAllTouhou.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CharacterDTO {
    private Long id;
    private String nombre;
    private int edad;
    private String genero;
    private int altura;
    private int peso;
    private String especie;
    private String biografia;
    private String imagen; // URL de la imagen titulada como "first"
}

package com.example.demoAllTouhou.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CharacterDTO {
    private Long id;
    private String name;
    private int age;
    private String gender;
    private int height;
    private int weight;
    private String biography;
    private String relations;
    private String importantFacts;
    private String species;
}

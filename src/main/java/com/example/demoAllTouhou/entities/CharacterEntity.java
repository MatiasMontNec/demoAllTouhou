package com.example.demoAllTouhou.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "character")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CharacterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String name;
    private int age;
    private String gender;
    private int height;
    private int weight;
    private String biography;
    private String livesIn;
    private int groupSpecies;
    private String link1;
    private String link2;
    private String imagePath1;
    private String imagePath2;
    private String imagePath3;
    private String imagePath4;
    private String imagePath5;
}

package com.example.demoAllTouhou.entities;

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
    private String relations;
    private String importantFacts;
}

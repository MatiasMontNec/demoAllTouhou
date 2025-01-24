package com.example.demoAllTouhou.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    @ManyToMany(mappedBy = "characterId", cascade = CascadeType.ALL)
    private List<DislikesEntity> dislikes;

    @ManyToMany(mappedBy = "characterId", cascade = CascadeType.ALL)
    private List<LikesEntity> likes;

    @ManyToMany(mappedBy = "characterId", cascade = CascadeType.ALL)
    private List<PowersEntity> powers;

    @ManyToMany(mappedBy = "characterId", cascade = CascadeType.ALL)
    private List<SpeciesEntity> species;

    // Es parte de...
    @ManyToMany(mappedBy = "characterId", cascade = CascadeType.ALL)
    private List<GameEntity> games;

    @ManyToMany(mappedBy = "characterId", cascade = CascadeType.ALL)
    private List<MangaEntity> manga;

    @ManyToMany(mappedBy = "characterId", cascade = CascadeType.ALL)
    private List<SongEntity> songs;

    // Tiene
    @ManyToMany(mappedBy = "characterId", cascade = CascadeType.ALL)
    private List<MercancyEntity> mercancy;

    @ManyToMany(mappedBy = "characterId", cascade = CascadeType.ALL)
    private List<ImagesEntity> images;
}

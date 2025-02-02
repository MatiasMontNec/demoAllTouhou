package com.example.demoAllTouhou.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String livesIn;
    private int groupSpecies;

    @OneToMany(mappedBy = "character", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<CharacterRelationEntity> characterRelations;

    @OneToMany(mappedBy = "relatedCharacter", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<CharacterRelationEntity> relatedCharacterRelations;

    @ManyToMany(mappedBy = "characters", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DislikesEntity> dislikes;

    @ManyToMany(mappedBy = "characters", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LikesEntity> likes;

    @ManyToMany(mappedBy = "characters", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PowersEntity> powers;

    @ManyToMany(mappedBy = "characters", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SpeciesEntity> species;

    // Es parte de...
    @ManyToMany(mappedBy = "characters", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<GameEntity> games;

    @ManyToMany(mappedBy = "characters", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<MangaEntity> manga;

    @ManyToMany(mappedBy = "characters", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<SongEntity> songs;

    // Tiene
    @ManyToMany(mappedBy = "characters", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<MerchEntity> merch;

    @ManyToMany(mappedBy = "characters", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ImagesEntity> images;
}
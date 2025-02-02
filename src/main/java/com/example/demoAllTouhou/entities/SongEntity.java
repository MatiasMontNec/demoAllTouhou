package com.example.demoAllTouhou.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "song")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SongEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private String title;
    private String artist;
    private String description;
    private String lyrics;

    @ManyToMany
    @JoinTable(
            name = "song_character",
            joinColumns = @JoinColumn(name = "song_id"),
            inverseJoinColumns = @JoinColumn(name = "character_id")
    )
    @JsonManagedReference
    private List<CharacterEntity> characters;

    // Relación con ImagesEntity
    @ManyToMany
    @JoinTable(
            name = "song_images",
            joinColumns = @JoinColumn(name = "song_id"),
            inverseJoinColumns = @JoinColumn(name = "images_id")
    )
    @JsonManagedReference
    private List<ImagesEntity> images;
}
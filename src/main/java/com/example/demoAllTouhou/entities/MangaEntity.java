package com.example.demoAllTouhou.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "manga")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MangaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private String title;
    private String author;
    private String description;
    private String accessLink;

    @ManyToMany
    @JoinTable(
        name = "manga_character",
        joinColumns = @JoinColumn(name = "manga_id"),
        inverseJoinColumns = @JoinColumn(name = "character_id")
    )
    @JsonManagedReference
    private List<CharacterEntity> characters;
}

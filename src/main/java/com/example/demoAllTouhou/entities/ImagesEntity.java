package com.example.demoAllTouhou.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "images")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImagesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private String name;
    private String description;
    @Lob
    @Column(nullable = false)
    private byte[] image;

    @ManyToMany
    @JoinTable(
        name = "images_character",
        joinColumns = @JoinColumn(name = "images_id"),
        inverseJoinColumns = @JoinColumn(name = "character_id")
    )
    @JsonManagedReference
    private List<CharacterEntity> characters;
}

package com.example.demoAllTouhou.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "species")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpeciesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private String name;
    private String description;
    private String type;

    @ManyToMany
    @JoinTable(
        name = "species_character",
        joinColumns = @JoinColumn(name = "species_id"),
        inverseJoinColumns = @JoinColumn(name = "character_id")
    )
    @JsonManagedReference
    private List<CharacterEntity> characters;
}

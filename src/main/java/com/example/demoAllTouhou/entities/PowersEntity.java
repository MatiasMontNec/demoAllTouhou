package com.example.demoAllTouhou.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "powers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PowersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private String name;
    private String description;
    
    @ManyToMany
    @JoinTable(
        name = "powers_character",
        joinColumns = @JoinColumn(name = "powers_id"),
        inverseJoinColumns = @JoinColumn(name = "character_id")
    )
    @JsonManagedReference
    private List<CharacterEntity> characters;
}

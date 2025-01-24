package com.example.demoAllTouhou.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "likes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private String name;
    private String description;
    
    @ManyToMany
    @JoinTable(
        name = "likes_character",
        joinColumns = @JoinColumn(name = "likes_id"),
        inverseJoinColumns = @JoinColumn(name = "character_id")
    )
    private List<CharacterEntity> characters;
}
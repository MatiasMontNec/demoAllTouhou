package com.example.demoAllTouhou.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "merch")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MerchEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private String name;
    private String description;
    private int price;
    private LocalDate updateDate;
    private String link;

    @ManyToMany
    @JoinTable(
            name = "merch_character",
            joinColumns = @JoinColumn(name = "merch_id"),
            inverseJoinColumns = @JoinColumn(name = "character_id")
    )
    private List<CharacterEntity> characters;

    // Relación con ImagesEntity
    @ManyToMany
    @JoinTable(
            name = "merch_images",
            joinColumns = @JoinColumn(name = "merch_id"),
            inverseJoinColumns = @JoinColumn(name = "images_id")
    )
    private List<ImagesEntity> images;
}
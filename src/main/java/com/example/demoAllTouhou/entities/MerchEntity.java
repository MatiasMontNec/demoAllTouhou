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

    // Relación con CharacterEntity (Muchos a Uno)
    @ManyToOne
    @JoinColumn(name = "character_id", nullable = false)
    private CharacterEntity character;

    // Relación con ImagesEntity
    @ManyToMany
    @JoinTable(
            name = "merch_images",
            joinColumns = @JoinColumn(name = "merch_id"),
            inverseJoinColumns = @JoinColumn(name = "images_id")
    )
    private List<ImagesEntity> images;
}

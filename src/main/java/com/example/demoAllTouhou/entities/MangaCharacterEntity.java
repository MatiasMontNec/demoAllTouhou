package com.example.demoAllTouhou.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "mangaCharacter")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MangaCharacterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private long mangaId;
    private long characterId;
}

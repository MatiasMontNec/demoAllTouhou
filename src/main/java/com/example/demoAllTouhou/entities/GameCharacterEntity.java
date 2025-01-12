package com.example.demoAllTouhou.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "gameCharacter")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameCharacterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private long characterId;
    private long gameId;
}

package com.example.demoAllTouhou.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}

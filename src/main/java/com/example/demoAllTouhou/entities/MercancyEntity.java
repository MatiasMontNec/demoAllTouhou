package com.example.demoAllTouhou.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "mercancy")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MercancyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private long characterId;
    private String name;
    private String description;
    private int price;
    private LocalDate updateDate;
    private String link;
}

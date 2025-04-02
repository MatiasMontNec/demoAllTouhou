package com.example.demoAllTouhou.repositories;

import com.example.demoAllTouhou.entities.CharacterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CharacterRepository extends JpaRepository<CharacterEntity, Long> {

    // Encontrar un personaje por nombre
    Optional<CharacterEntity> findByName(String name);

    // Encontrar personajes con edad menor o igual
    List<CharacterEntity> findByAgeLessThanEqual(int age);

    // Encontrar personajes con edad mayor o igual
    List<CharacterEntity> findByAgeGreaterThanEqual(int age);

    // Encontrar personajes con altura mayor
    List<CharacterEntity> findByHeightGreaterThan(int height);

    // Encontrar personajes con altura menor
    List<CharacterEntity> findByHeightLessThan(int height);

    // Encontrar personajes con peso mayor
    List<CharacterEntity> findByWeightGreaterThan(int weight);

    // Encontrar personajes con peso menor
    List<CharacterEntity> findByWeightLessThan(int weight);

    // Encontrar personajes que contienen texto en importantFacts (case insensitive)
    //List<CharacterEntity> findByImportantFactsContainingIgnoreCase(String keyword);

    //List<CharacterEntity> findBySpecies_NameIgnoreCase(String species);
}

package com.example.demoAllTouhou.repositories;

import com.example.demoAllTouhou.entities.SpeciesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpeciesRepository extends JpaRepository<SpeciesEntity, Long> {

    // Buscar Species cuyo nombre contiene un texto (ignorar mayúsculas/minúsculas)
    List<SpeciesEntity> findByNameContainingIgnoreCase(String title);

    // Buscar Species cuyo tipo contiene un texto (ignorar mayúsculas/minúsculas)
    List<SpeciesEntity> findByTypeContainingIgnoreCase(String artist);
}

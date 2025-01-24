package com.example.demoAllTouhou.repositories;

import com.example.demoAllTouhou.entities.SpeciesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpeciesRepository extends JpaRepository<SpeciesEntity, Long> {

    // Buscar Speciess cuyo título contiene un texto (ignorar mayúsculas/minúsculas)
    List<SpeciesEntity> findByTitleContainingIgnoreCase(String title);

    // Buscar Speciess cuyo artista contiene un texto (ignorar mayúsculas/minúsculas)
    List<SpeciesEntity> findByArtistContainingIgnoreCase(String artist);
}

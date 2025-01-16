package com.example.demoAllTouhou.repositories;

import com.example.demoAllTouhou.entities.SongEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<SongEntity, Long> {

    // Buscar Songs cuyo título contiene un texto (ignorar mayúsculas/minúsculas)
    List<SongEntity> findByTitleContainingIgnoreCase(String title);

    // Buscar Songs cuyo artista contiene un texto (ignorar mayúsculas/minúsculas)
    List<SongEntity> findByArtistContainingIgnoreCase(String artist);
}

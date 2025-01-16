package com.example.demoAllTouhou.repositories;

import com.example.demoAllTouhou.entities.MangaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MangaRepository extends JpaRepository<MangaEntity, Long> {

    // Buscar Mangas por título (que contenga el texto, ignorando mayúsculas/minúsculas)
    List<MangaEntity> findByTitleContainingIgnoreCase(String title);

    // Buscar Mangas por autor (que contenga el texto, ignorando mayúsculas/minúsculas)
    List<MangaEntity> findByAuthorContainingIgnoreCase(String author);
}

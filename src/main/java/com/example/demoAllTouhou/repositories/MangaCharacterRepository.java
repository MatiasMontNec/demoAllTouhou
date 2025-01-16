package com.example.demoAllTouhou.repositories;

import com.example.demoAllTouhou.entities.MangaCharacterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MangaCharacterRepository extends JpaRepository<MangaCharacterEntity, Long> {

    // Buscar MangaCharacter por mangaId
    List<MangaCharacterEntity> findByMangaId(Long mangaId);

    // Buscar MangaCharacter por characterId
    List<MangaCharacterEntity> findByCharacterId(Long characterId);
}

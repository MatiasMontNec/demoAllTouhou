package com.example.demoAllTouhou.repositories;

import com.example.demoAllTouhou.entities.CharacterSongEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterSongRepository extends JpaRepository<CharacterSongEntity, Long> {

    // Obtener lista de Character IDs por Song ID
    @Query("SELECT c.characterId FROM CharacterSongEntity c WHERE c.songId = :songId")
    List<Long> findCharacterIdsBySongId(@Param("songId") Long songId);

    // Obtener lista de Song IDs por Character ID
    @Query("SELECT c.songId FROM CharacterSongEntity c WHERE c.characterId = :characterId")
    List<Long> findSongIdsByCharacterId(@Param("characterId") Long characterId);
}

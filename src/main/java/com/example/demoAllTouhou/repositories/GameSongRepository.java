package com.example.demoAllTouhou.repositories;

import com.example.demoAllTouhou.entities.GameSongEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameSongRepository extends JpaRepository<GameSongEntity, Long> {

    // Obtener todos los GameSong por songId
    List<GameSongEntity> findBySongId(Long songId);

    // Obtener todos los GameSong por gameId
    List<GameSongEntity> findByGameId(Long gameId);
}

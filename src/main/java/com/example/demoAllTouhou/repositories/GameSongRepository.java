package com.example.demoAllTouhou.repositories;

import com.example.demoAllTouhou.entities.GameSongEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameSongRepository extends JpaRepository<GameSongEntity,Long> {
}
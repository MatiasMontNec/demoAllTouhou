package com.example.demoAllTouhou.repositories;

import com.example.demoAllTouhou.entities.GameCharacterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameCharacterRepository extends JpaRepository<GameCharacterEntity, Long> {

    // Obtener todos los GameCharacter por Character ID
    List<GameCharacterEntity> findByCharacterId(Long characterId);

    // Obtener todos los GameCharacter por Game ID
    List<GameCharacterEntity> findByGameId(Long gameId);
}

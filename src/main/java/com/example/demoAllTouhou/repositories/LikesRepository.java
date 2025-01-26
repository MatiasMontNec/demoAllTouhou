package com.example.demoAllTouhou.repositories;

import com.example.demoAllTouhou.entities.LikesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikesRepository extends JpaRepository<LikesEntity, Long> {

    // Buscar likes por parte del nombre (case-insensitive)
    List<LikesEntity> findByNameContainingIgnoreCase(String name);

    // Obtener likes por characterId
    List<LikesEntity> findByCharacters_Id(Long characterId);
}

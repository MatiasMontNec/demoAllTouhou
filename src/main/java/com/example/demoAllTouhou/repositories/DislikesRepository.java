package com.example.demoAllTouhou.repositories;

import com.example.demoAllTouhou.entities.DislikesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DislikesRepository extends JpaRepository<DislikesEntity, Long> {

    // Obtener lista de Dislikes por Character ID
    List<DislikesEntity> findByCharacterId(Long characterId);
}

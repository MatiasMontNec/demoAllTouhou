package com.example.demoAllTouhou.repositories;

import com.example.demoAllTouhou.entities.PowersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PowersRepository extends JpaRepository<PowersEntity, Long> {

    // Buscar Powers cuyo nombre contenga un texto (ignorar mayúsculas/minúsculas)
    List<PowersEntity> findByNameContainingIgnoreCase(String name);

    // Buscar Powers por Character ID
    List<PowersEntity> findByCharacters_Id(long characterId);
}

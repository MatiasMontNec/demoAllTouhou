package com.example.demoAllTouhou.repositories;

import com.example.demoAllTouhou.entities.ImagesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImagesRepository extends JpaRepository<ImagesEntity, Long> {

    // Buscar imágenes por parte del nombre (case-insensitive)
    List<ImagesEntity> findByNameContainingIgnoreCase(String name);
}

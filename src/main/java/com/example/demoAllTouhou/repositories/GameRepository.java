package com.example.demoAllTouhou.repositories;

import com.example.demoAllTouhou.entities.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<GameEntity, Long> {

    // Obtener un Game por su título
    Optional<GameEntity> findByTitle(String title);

    // Obtener todos los Games cuya fecha de lanzamiento sea antes de una fecha dada
    List<GameEntity> findByReleaseDateBefore(LocalDate date);

    // Obtener todos los Games cuya fecha de lanzamiento sea después de una fecha dada
    List<GameEntity> findByReleaseDateAfter(LocalDate date);
}

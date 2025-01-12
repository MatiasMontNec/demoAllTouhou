package com.example.demoAllTouhou.repositories;

import com.example.demoAllTouhou.entities.MangaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MangaRepository extends JpaRepository<MangaEntity,Long> {
}
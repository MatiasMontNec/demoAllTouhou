package com.example.demoAllTouhou.repositories;

import com.example.demoAllTouhou.entities.MangaCharacterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MangaCharacterRepository extends JpaRepository<MangaCharacterEntity,Long> {
}
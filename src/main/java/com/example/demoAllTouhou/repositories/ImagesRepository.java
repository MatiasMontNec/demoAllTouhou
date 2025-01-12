package com.example.demoAllTouhou.repositories;

import com.example.demoAllTouhou.entities.ImagesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagesRepository extends JpaRepository<ImagesEntity,Long> {
}
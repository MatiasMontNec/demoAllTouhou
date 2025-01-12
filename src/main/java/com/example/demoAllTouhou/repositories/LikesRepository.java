package com.example.demoAllTouhou.repositories;

import com.example.demoAllTouhou.entities.LikesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikesRepository extends JpaRepository<LikesEntity,Long> {
}
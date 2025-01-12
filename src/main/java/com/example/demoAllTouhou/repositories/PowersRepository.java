package com.example.demoAllTouhou.repositories;

import com.example.demoAllTouhou.entities.PowersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PowersRepository extends JpaRepository<PowersEntity,Long> {
}
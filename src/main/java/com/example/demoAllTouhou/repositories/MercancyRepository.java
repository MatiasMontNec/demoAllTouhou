package com.example.demoAllTouhou.repositories;

import com.example.demoAllTouhou.entities.MercancyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MercancyRepository extends JpaRepository<MercancyEntity,Long> {
}
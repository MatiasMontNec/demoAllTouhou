package com.example.demoAllTouhou.repositories;

import com.example.demoAllTouhou.entities.MercancyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MercancyRepository extends JpaRepository<MercancyEntity, Long> {

    // Buscar Mercancías por Character ID
    List<MercancyEntity> findByCharacterId(long characterId);

    // Buscar Mercancías cuyo nombre contenga un texto (ignorar mayúsculas/minúsculas)
    List<MercancyEntity> findByNameContainingIgnoreCase(String name);

    // Buscar Mercancías con precio superior al ingresado
    List<MercancyEntity> findByPriceGreaterThan(int price);

    // Buscar Mercancías con precio inferior al ingresado
    List<MercancyEntity> findByPriceLessThan(int price);
}

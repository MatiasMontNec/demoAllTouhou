package com.example.demoAllTouhou.repositories;

import com.example.demoAllTouhou.entities.MerchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MerchRepository extends JpaRepository<MerchEntity, Long> {

    // Buscar Mercancías por Character ID
    List<MerchEntity> findByCharacters_Id(Long characterId);

    // Buscar Mercancías cuyo nombre contenga un texto (ignorar mayúsculas/minúsculas)
    List<MerchEntity> findByNameContainingIgnoreCase(String name);

    // Buscar Mercancías con precio superior al ingresado
    List<MerchEntity> findByPriceGreaterThan(int price);

    // Buscar Mercancías con precio inferior al ingresado
    List<MerchEntity> findByPriceLessThan(int price);
}

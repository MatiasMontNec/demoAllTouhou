package com.example.demoAllTouhou.services;

import com.example.demoAllTouhou.entities.MercancyEntity;
import com.example.demoAllTouhou.repositories.MercancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MercancyService {

    @Autowired
    private MercancyRepository mercancyRepository;

    // **Crear una nueva Mercancía**
    public MercancyEntity createMercancy(MercancyEntity mercancyEntity) {
        return mercancyRepository.save(mercancyEntity);
    }

    // **Obtener todas las Mercancías**
    public List<MercancyEntity> getAllMercancy() {
        return mercancyRepository.findAll();
    }

    // **Obtener Mercancía por ID**
    public MercancyEntity getMercancyById(Long id) {
        return mercancyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mercancy not found with id: " + id));
    }

    // **Actualizar una Mercancía**
    public MercancyEntity updateMercancy(Long id, MercancyEntity updatedMercancy) {
        MercancyEntity existingMercancy = mercancyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mercancy not found with id: " + id));

        existingMercancy.setCharacterId(updatedMercancy.getCharacterId());
        existingMercancy.setName(updatedMercancy.getName());
        existingMercancy.setDescription(updatedMercancy.getDescription());
        existingMercancy.setPrice(updatedMercancy.getPrice());
        existingMercancy.setUpdateDate(updatedMercancy.getUpdateDate());
        existingMercancy.setLink(updatedMercancy.getLink());

        return mercancyRepository.save(existingMercancy);
    }

    // **Eliminar una Mercancía por ID**
    public void deleteMercancy(Long id) {
        if (!mercancyRepository.existsById(id)) {
            throw new RuntimeException("Mercancy not found with id: " + id);
        }
        mercancyRepository.deleteById(id);
    }

    // **Obtener Mercancías por Character ID**
    public List<MercancyEntity> getMercancyByCharacterId(long characterId) {
        return mercancyRepository.findByCharacterId(characterId);
    }

    // **Obtener Mercancías que contienen un texto en el nombre**
    public List<MercancyEntity> getMercancyByName(String name) {
        return mercancyRepository.findByNameContainingIgnoreCase(name);
    }

    // **Obtener Mercancías con precio superior al ingresado**
    public List<MercancyEntity> getMercancyByPriceGreaterThan(int price) {
        return mercancyRepository.findByPriceGreaterThan(price);
    }

    // **Obtener Mercancías con precio inferior al ingresado**
    public List<MercancyEntity> getMercancyByPriceLessThan(int price) {
        return mercancyRepository.findByPriceLessThan(price);
    }
}

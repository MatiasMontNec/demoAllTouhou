package com.example.demoAllTouhou.services;

import com.example.demoAllTouhou.entities.MerchEntity;
import com.example.demoAllTouhou.repositories.MerchRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MerchService {

    private final MerchRepository merchRepository;

    public MerchService(MerchRepository merchRepository) {
        this.merchRepository = merchRepository;
    }

    // **Crear una nueva Mercancía**
    public MerchEntity createMerch(MerchEntity merchEntity) {
        return merchRepository.save(merchEntity);
    }

    // **Obtener todas las Mercancías**
    public List<MerchEntity> getAllMerch() {
        return merchRepository.findAll();
    }

    // **Obtener Mercancía por ID**
    public MerchEntity getMerchById(Long id) {
        return merchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("merch not found with id: " + id));
    }

    // **Actualizar una Mercancía**
    public MerchEntity updateMerch(Long id, MerchEntity updatedMerch) {
        MerchEntity existingMerch = merchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("merch not found with id: " + id));

        if (updatedMerch.getName() != null && !updatedMerch.getName().isEmpty()) existingMerch.setName(updatedMerch.getName());
        if (updatedMerch.getDescription() != null && !updatedMerch.getDescription().isEmpty()) existingMerch.setDescription(updatedMerch.getDescription());
        if (updatedMerch.getPrice()>0) existingMerch.setPrice(updatedMerch.getPrice());
        if (updatedMerch.getUpdateDate() != null && !updatedMerch.getUpdateDate().isEqual(LocalDate.now())) existingMerch.setUpdateDate(updatedMerch.getUpdateDate());
        if (updatedMerch.getLink() != null && !updatedMerch.getLink().isEmpty()) existingMerch.setLink(updatedMerch.getLink());

        return merchRepository.save(existingMerch);
    }

    // **Eliminar una Mercancía por ID**
    public void deleteMerch(Long id) {
        if (!merchRepository.existsById(id)) {
            throw new RuntimeException("merch not found with id: " + id);
        }
        merchRepository.deleteById(id);
    }

    // **Obtener Mercancías por Character ID**
    public List<MerchEntity> getMerchByCharacterId(long characterId) {
        return merchRepository.findByCharacters_Id(characterId);
    }

    // **Obtener Mercancías que contienen un texto en el nombre**
    public List<MerchEntity> getMerchByName(String name) {
        return merchRepository.findByNameContainingIgnoreCase(name);
    }

    // **Obtener Mercancías con precio superior al ingresado**
    public List<MerchEntity> getMerchByPriceGreaterThan(int price) {
        return merchRepository.findByPriceGreaterThan(price);
    }

    // **Obtener Mercancías con precio inferior al ingresado**
    public List<MerchEntity> getMerchByPriceLessThan(int price) {
        return merchRepository.findByPriceLessThan(price);
    }
}

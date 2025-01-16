package com.example.demoAllTouhou.services;

import com.example.demoAllTouhou.entities.PowersEntity;
import com.example.demoAllTouhou.repositories.PowersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PowersService {

    @Autowired
    private PowersRepository powersRepository;

    // **Crear un nuevo Power**
    public PowersEntity createPower(PowersEntity powersEntity) {
        return powersRepository.save(powersEntity);
    }

    // **Obtener todos los Powers**
    public List<PowersEntity> getAllPowers() {
        return powersRepository.findAll();
    }

    // **Obtener un Power por ID**
    public PowersEntity getPowerById(Long id) {
        return powersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Power not found with id: " + id));
    }

    // **Actualizar un Power**
    public PowersEntity updatePower(Long id, PowersEntity updatedPower) {
        PowersEntity existingPower = powersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Power not found with id: " + id));

        existingPower.setName(updatedPower.getName());
        existingPower.setDescription(updatedPower.getDescription());
        existingPower.setCharacterId(updatedPower.getCharacterId());

        return powersRepository.save(existingPower);
    }

    // **Eliminar un Power por ID**
    public void deletePower(Long id) {
        if (!powersRepository.existsById(id)) {
            throw new RuntimeException("Power not found with id: " + id);
        }
        powersRepository.deleteById(id);
    }

    // **Obtener Powers por Name (contiene string)**
    public List<PowersEntity> getPowersByName(String name) {
        return powersRepository.findByNameContainingIgnoreCase(name);
    }

    // **Obtener Powers por Character ID**
    public List<PowersEntity> getPowersByCharacterId(long characterId) {
        return powersRepository.findByCharacterId(characterId);
    }
}

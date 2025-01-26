package com.example.demoAllTouhou.services;

import com.example.demoAllTouhou.entities.PowersEntity;
import com.example.demoAllTouhou.repositories.PowersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PowersService {

    private final PowersRepository powersRepository;

    public PowersService(PowersRepository powersRepository) {
        this.powersRepository = powersRepository;
    }

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

        if (updatedPower.getName() !=null && !updatedPower.getName().isEmpty()) existingPower.setName(updatedPower.getName());
        if (updatedPower.getDescription() !=null && !updatedPower.getDescription().isEmpty()) existingPower.setDescription(updatedPower.getDescription());

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
        return powersRepository.findByCharacters_Id(characterId);
    }
}

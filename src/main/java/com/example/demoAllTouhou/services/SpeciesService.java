package com.example.demoAllTouhou.services;

import com.example.demoAllTouhou.entities.SpeciesEntity;
import com.example.demoAllTouhou.repositories.SpeciesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpeciesService {

    private final SpeciesRepository SpeciesRepository;

    public SpeciesService(SpeciesRepository SpeciesRepository) {
        this.SpeciesRepository = SpeciesRepository;
    }

    // **Crear una nueva Species**
    public SpeciesEntity createSpecies(SpeciesEntity SpeciesEntity) {
        return SpeciesRepository.save(SpeciesEntity);
    }

    // **Obtener todas las Speciess**
    public List<SpeciesEntity> getAllSpecies() {
        return SpeciesRepository.findAll();
    }

    // **Obtener una Species por ID**
    public SpeciesEntity getSpeciesById(Long id) {
        return SpeciesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Species not found with id: " + id));
    }

    // **Actualizar una Species**
    public SpeciesEntity updateSpecies(Long id, SpeciesEntity updatedSpecies) {
        SpeciesEntity existingSpecies = SpeciesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Species not found with id: " + id));

        if (updatedSpecies.getName() != null && !updatedSpecies.getName().isEmpty()) existingSpecies.setName(updatedSpecies.getName());
        if (updatedSpecies.getDescription() != null && !updatedSpecies.getDescription().isEmpty()) existingSpecies.setDescription(updatedSpecies.getDescription());
        if (updatedSpecies.getType() != null && !updatedSpecies.getType().isEmpty()) existingSpecies.setType(updatedSpecies.getType());

        return SpeciesRepository.save(existingSpecies);
    }

    // **Eliminar una Species por ID**
    public void deleteSpecies(Long id) {
        if (!SpeciesRepository.existsById(id)) {
            throw new RuntimeException("Species not found with id: " + id);
        }
        SpeciesRepository.deleteById(id);
    }

    // **Obtener especie cuyo nombre contiene un texto**
    public List<SpeciesEntity> getSpeciesByName(String name) {
        return SpeciesRepository.findByNameContainingIgnoreCase(name);
    }

    // **Obtener especie cuyo tipo contiene un texto**
    public List<SpeciesEntity> getSpeciesByType(String type) {
        return SpeciesRepository.findByTypeContainingIgnoreCase(type);
    }
}

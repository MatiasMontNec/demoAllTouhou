package com.example.demoAllTouhou.services;

import com.example.demoAllTouhou.entities.SpeciesEntity;
import com.example.demoAllTouhou.repositories.SpeciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpeciesService {

    @Autowired
    private SpeciesRepository SpeciesRepository;

    // **Crear una nueva Species**
    public SpeciesEntity createSpecies(SpeciesEntity SpeciesEntity) {
        return SpeciesRepository.save(SpeciesEntity);
    }

    // **Obtener todas las Speciess**
    public List<SpeciesEntity> getAllSpeciess() {
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
        existingSpecies.setName(updatedSpecies.getName());
        existingSpecies.setDescription(updatedSpecies.getDescription());
        existingSpecies.setType(updatedSpecies.getType());
        existingSpecies.setCharacters(updatedSpecies.getCharacters());

        return SpeciesRepository.save(existingSpecies);
    }

    // **Eliminar una Species por ID**
    public void deleteSpecies(Long id) {
        if (!SpeciesRepository.existsById(id)) {
            throw new RuntimeException("Species not found with id: " + id);
        }
        SpeciesRepository.deleteById(id);
    }

    // **Obtener Speciess cuyo título contiene un texto**
    public List<SpeciesEntity> getSpeciessByTitle(String title) {
        return SpeciesRepository.findByTitleContainingIgnoreCase(title);
    }

    // **Obtener Speciess cuyo artista contiene un texto**
    public List<SpeciesEntity> getSpeciessByArtist(String artist) {
        return SpeciesRepository.findByArtistContainingIgnoreCase(artist);
    }
}

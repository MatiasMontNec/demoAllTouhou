package com.example.demoAllTouhou.controllers;

import com.example.demoAllTouhou.entities.SpeciesEntity;
import com.example.demoAllTouhou.models.IdListDTO;
import com.example.demoAllTouhou.services.SpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/species")
public class SpeciesController {

    @Autowired
    private SpeciesService speciesService;

    // **Crear una nueva Species**
    @PostMapping("/create")
    public ResponseEntity<SpeciesEntity> createSpecies(@RequestBody SpeciesEntity speciesEntity) {
        SpeciesEntity createdSpecies = speciesService.createSpecies(speciesEntity);
        return ResponseEntity.ok(createdSpecies);
    }

    // **Obtener todas las Speciess**
    @GetMapping("/all")
    public ResponseEntity<List<SpeciesEntity>> getAllSpecies() {
        List<SpeciesEntity> speciess = speciesService.getAllSpeciess();
        return ResponseEntity.ok(speciess);
    }

    // **Obtener una Species por ID**
    @GetMapping("/{id}")
    public ResponseEntity<SpeciesEntity> getSpeciesById(@PathVariable Long id) {
        SpeciesEntity species = speciesService.getSpeciesById(id);
        return ResponseEntity.ok(species);
    }

    // **Actualizar una Species**
    @PutMapping("/{id}")
    public ResponseEntity<SpeciesEntity> updateSpecies(@PathVariable Long id, @RequestBody SpeciesEntity updatedSpecies) {
        SpeciesEntity species = speciesService.updateSpecies(id, updatedSpecies);
        return ResponseEntity.ok(species);
    }

    // **Eliminar una Species por ID**
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpecies(@PathVariable Long id) {
        speciesService.deleteSpecies(id);
        return ResponseEntity.noContent().build();
    }


    // **Obtener Speciess por lista de IDs**
    @PostMapping("/by-ids")
    public ResponseEntity<List<SpeciesEntity>> getSpeciessByIds(@RequestBody IdListDTO idListDTO) {
        List<SpeciesEntity> speciess = idListDTO.getIds().stream()
                .map(speciesService::getSpeciesById)
                .toList();
        return ResponseEntity.ok(speciess);
    }
}

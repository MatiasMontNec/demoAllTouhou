package com.example.demoAllTouhou.controllers;

import com.example.demoAllTouhou.entities.PowersEntity;
import com.example.demoAllTouhou.dtos.IdListDTO;
import com.example.demoAllTouhou.services.PowersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/powers")
public class PowersController {

    @Autowired
    private PowersService powersService;

    // **Crear un nuevo Power**
    @PostMapping("/create")
    public ResponseEntity<PowersEntity> createPower(@RequestBody PowersEntity powersEntity) {
        PowersEntity createdPower = powersService.createPower(powersEntity);
        return ResponseEntity.ok(createdPower);
    }

    // **Obtener todos los Powers**
    @GetMapping("/all")
    public ResponseEntity<List<PowersEntity>> getAllPowers() {
        List<PowersEntity> powers = powersService.getAllPowers();
        return ResponseEntity.ok(powers);
    }

    // **Obtener un Power por ID**
    @GetMapping("/{id}")
    public ResponseEntity<PowersEntity> getPowerById(@PathVariable Long id) {
        PowersEntity power = powersService.getPowerById(id);
        return ResponseEntity.ok(power);
    }

    // **Actualizar un Power**
    @PutMapping("/{id}")
    public ResponseEntity<PowersEntity> updatePower(@PathVariable Long id, @RequestBody PowersEntity updatedPower) {
        PowersEntity power = powersService.updatePower(id, updatedPower);
        return ResponseEntity.ok(power);
    }

    // **Eliminar un Power por ID**
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePower(@PathVariable Long id) {
        powersService.deletePower(id);
        return ResponseEntity.noContent().build();
    }

    // **Obtener Powers por Name (contiene string)**
    @GetMapping("/search/name")
    public ResponseEntity<List<PowersEntity>> getPowersByName(@RequestParam String name) {
        List<PowersEntity> powers = powersService.getPowersByName(name);
        return ResponseEntity.ok(powers);
    }

    // **Obtener Powers por Character ID**
    @GetMapping("/character/{characterId}")
    public ResponseEntity<List<PowersEntity>> getPowersByCharacterId(@PathVariable long characterId) {
        List<PowersEntity> powers = powersService.getPowersByCharacterId(characterId);
        return ResponseEntity.ok(powers);
    }

    // **Obtener múltiples Powers por lista de IDs**
    @PostMapping("/by-ids")
    public ResponseEntity<List<PowersEntity>> getPowersByIds(@RequestBody IdListDTO idListDTO) {
        List<PowersEntity> powers = idListDTO.getIds().stream()
                .map(powersService::getPowerById)
                .toList();
        return ResponseEntity.ok(powers);
    }
}

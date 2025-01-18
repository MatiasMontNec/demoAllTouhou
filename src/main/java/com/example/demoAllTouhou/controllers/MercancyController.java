package com.example.demoAllTouhou.controllers;

import com.example.demoAllTouhou.entities.MercancyEntity;
import com.example.demoAllTouhou.models.DateDTO;
import com.example.demoAllTouhou.models.MercancyDTO;
import com.example.demoAllTouhou.models.MercancyListDTO;
import com.example.demoAllTouhou.services.MercancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/mercancies")
public class MercancyController {

    @Autowired
    private MercancyService mercancyService;

    // **Crear una nueva Mercancía**
    @PostMapping
    public ResponseEntity<MercancyDTO> createMercancy(@RequestBody MercancyDTO mercancyDTO) {
        MercancyEntity entity = mapToEntity(mercancyDTO);
        MercancyEntity createdEntity = mercancyService.createMercancy(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToDTO(createdEntity));
    }

    // **Obtener todas las Mercancías**
    @GetMapping
    public ResponseEntity<MercancyListDTO> getAllMercancy() {
        List<MercancyEntity> entities = mercancyService.getAllMercancy();
        List<MercancyDTO> dtos = entities.stream().map(this::mapToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(new MercancyListDTO(dtos));
    }

    // **Obtener Mercancía por ID**
    @GetMapping("/{id}")
    public ResponseEntity<MercancyDTO> getMercancyById(@PathVariable Long id) {
        MercancyEntity entity = mercancyService.getMercancyById(id);
        return ResponseEntity.ok(mapToDTO(entity));
    }

    // **Actualizar una Mercancía**
    @PutMapping("/{id}")
    public ResponseEntity<MercancyDTO> updateMercancy(@PathVariable Long id, @RequestBody MercancyDTO mercancyDTO) {
        MercancyEntity entity = mapToEntity(mercancyDTO);
        MercancyEntity updatedEntity = mercancyService.updateMercancy(id, entity);
        return ResponseEntity.ok(mapToDTO(updatedEntity));
    }

    // **Eliminar una Mercancía por ID**
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMercancy(@PathVariable Long id) {
        mercancyService.deleteMercancy(id);
        return ResponseEntity.noContent().build();
    }

    // **Obtener Mercancías por Character ID**
    @GetMapping("/by-character-id/{characterId}")
    public ResponseEntity<MercancyListDTO> getMercancyByCharacterId(@PathVariable long characterId) {
        List<MercancyEntity> entities = mercancyService.getMercancyByCharacterId(characterId);
        List<MercancyDTO> dtos = entities.stream().map(this::mapToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(new MercancyListDTO(dtos));
    }

    // **Obtener Mercancías que contienen un texto en el nombre**
    @GetMapping("/by-name")
    public ResponseEntity<MercancyListDTO> getMercancyByName(@RequestParam String name) {
        List<MercancyEntity> entities = mercancyService.getMercancyByName(name);
        List<MercancyDTO> dtos = entities.stream().map(this::mapToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(new MercancyListDTO(dtos));
    }

    // **Obtener Mercancías con precio superior al ingresado**
    @GetMapping("/by-price-greater-than/{price}")
    public ResponseEntity<MercancyListDTO> getMercancyByPriceGreaterThan(@PathVariable int price) {
        List<MercancyEntity> entities = mercancyService.getMercancyByPriceGreaterThan(price);
        List<MercancyDTO> dtos = entities.stream().map(this::mapToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(new MercancyListDTO(dtos));
    }

    // **Obtener Mercancías con precio inferior al ingresado**
    @GetMapping("/by-price-less-than/{price}")
    public ResponseEntity<MercancyListDTO> getMercancyByPriceLessThan(@PathVariable int price) {
        List<MercancyEntity> entities = mercancyService.getMercancyByPriceLessThan(price);
        List<MercancyDTO> dtos = entities.stream().map(this::mapToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(new MercancyListDTO(dtos));
    }

    // **Mapeo de DTO a Entidad**
    private MercancyEntity mapToEntity(MercancyDTO dto) {
        MercancyEntity entity = new MercancyEntity();
        entity.setId(dto.getId());
        entity.setCharacterId(dto.getCharacterId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setUpdateDate(dto.getUpdateDate() != null
                ? java.time.LocalDate.of(dto.getUpdateDate().getYear(), dto.getUpdateDate().getMonth(), dto.getUpdateDate().getDay())
                : null);
        entity.setLink(dto.getLink());
        return entity;
    }

    // **Mapeo de Entidad a DTO**
    private MercancyDTO mapToDTO(MercancyEntity entity) {
        return new MercancyDTO(
                entity.getId(),
                entity.getCharacterId(),
                entity.getName(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getUpdateDate() != null
                        ? new DateDTO(entity.getUpdateDate().getYear(), entity.getUpdateDate().getMonthValue(), entity.getUpdateDate().getDayOfMonth())
                        : null,
                entity.getLink()
        );
    }
}

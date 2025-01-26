package com.example.demoAllTouhou.controllers;

import com.example.demoAllTouhou.entities.MerchEntity;
import com.example.demoAllTouhou.dtos.DateDTO;
import com.example.demoAllTouhou.dtos.MerchDTO;
import com.example.demoAllTouhou.dtos.MerchListDTO;
import com.example.demoAllTouhou.services.MerchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/merch")
public class MerchController {

//    @Autowired
//    private MerchService merchService;
//
//    // **Crear una nueva Mercancía**
//    @PostMapping
//    public ResponseEntity<MerchDTO> createmerch(@RequestBody MerchDTO merchDTO) {
//        MerchEntity entity = mapToEntity(merchDTO);
//        MerchEntity createdEntity = merchService.createMerch(entity);
//        return ResponseEntity.status(HttpStatus.CREATED).body(mapToDTO(createdEntity));
//    }
//
//    // **Obtener todas las Mercancías**
//    @GetMapping
//    public ResponseEntity<MerchListDTO> getAllmerch() {
//        List<MerchEntity> entities = merchService.getAllMerch();
//        List<MerchDTO> dtos = entities.stream().map(this::mapToDTO).collect(Collectors.toList());
//        return ResponseEntity.ok(new MerchListDTO(dtos));
//    }
//
//    // **Obtener Mercancía por ID**
//    @GetMapping("/{id}")
//    public ResponseEntity<MerchDTO> getmerchById(@PathVariable Long id) {
//        MerchEntity entity = merchService.getMerchById(id);
//        return ResponseEntity.ok(mapToDTO(entity));
//    }
//
//    // **Actualizar una Mercancía**
//    @PutMapping("/{id}")
//    public ResponseEntity<MerchDTO> updatemerch(@PathVariable Long id, @RequestBody MerchDTO merchDTO) {
//        MerchEntity entity = mapToEntity(merchDTO);
//        MerchEntity updatedEntity = merchService.updateMerch(id, entity);
//        return ResponseEntity.ok(mapToDTO(updatedEntity));
//    }
//
//    // **Eliminar una Mercancía por ID**
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deletemerch(@PathVariable Long id) {
//        merchService.deleteMerch(id);
//        return ResponseEntity.noContent().build();
//    }
//
//    // **Obtener Mercancías por Character ID**
//    @GetMapping("/by-character-id/{characterId}")
//    public ResponseEntity<MerchListDTO> getmerchByCharacterId(@PathVariable long characterId) {
//        List<MerchEntity> entities = merchService.getMerchByCharacterId(characterId);
//        List<MerchDTO> dtos = entities.stream().map(this::mapToDTO).collect(Collectors.toList());
//        return ResponseEntity.ok(new MerchListDTO(dtos));
//    }
//
//    // **Obtener Mercancías que contienen un texto en el nombre**
//    @GetMapping("/by-name")
//    public ResponseEntity<MerchListDTO> getmerchByName(@RequestParam String name) {
//        List<MerchEntity> entities = merchService.getMerchByName(name);
//        List<MerchDTO> dtos = entities.stream().map(this::mapToDTO).collect(Collectors.toList());
//        return ResponseEntity.ok(new MerchListDTO(dtos));
//    }
//
//    // **Obtener Mercancías con precio superior al ingresado**
//    @GetMapping("/by-price-greater-than/{price}")
//    public ResponseEntity<MerchListDTO> getmerchByPriceGreaterThan(@PathVariable int price) {
//        List<MerchEntity> entities = merchService.getMerchByPriceGreaterThan(price);
//        List<MerchDTO> dtos = entities.stream().map(this::mapToDTO).collect(Collectors.toList());
//        return ResponseEntity.ok(new MerchListDTO(dtos));
//    }
//
//    // **Obtener Mercancías con precio inferior al ingresado**
//    @GetMapping("/by-price-less-than/{price}")
//    public ResponseEntity<MerchListDTO> getmerchByPriceLessThan(@PathVariable int price) {
//        List<MerchEntity> entities = merchService.getMerchByPriceLessThan(price);
//        List<MerchDTO> dtos = entities.stream().map(this::mapToDTO).collect(Collectors.toList());
//        return ResponseEntity.ok(new MerchListDTO(dtos));
//    }
//
//    // **Mapeo de DTO a Entidad**
//    private MerchEntity mapToEntity(MerchDTO dto) {
//        MerchEntity entity = new MerchEntity();
//        entity.setId(dto.getId());
//        entity.setCharacterId(dto.getCharacterId());
//        entity.setName(dto.getName());
//        entity.setDescription(dto.getDescription());
//        entity.setPrice(dto.getPrice());
//        entity.setUpdateDate(dto.getUpdateDate() != null
//                ? java.time.LocalDate.of(dto.getUpdateDate().getYear(), dto.getUpdateDate().getMonth(), dto.getUpdateDate().getDay())
//                : null);
//        entity.setLink(dto.getLink());
//        return entity;
//    }
//
//    // **Mapeo de Entidad a DTO**
//    private MerchDTO mapToDTO(MerchEntity entity) {
//        return new MerchDTO(
//                entity.getId(),
//                entity.getCharacterId(),
//                entity.getName(),
//                entity.getDescription(),
//                entity.getPrice(),
//                entity.getUpdateDate() != null
//                        ? new DateDTO(entity.getUpdateDate().getYear(), entity.getUpdateDate().getMonthValue(), entity.getUpdateDate().getDayOfMonth())
//                        : null,
//                entity.getLink()
//        );
//    }
}

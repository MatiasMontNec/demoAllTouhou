package com.example.demoAllTouhou.controllers;

import com.example.demoAllTouhou.entities.MangaEntity;
import com.example.demoAllTouhou.models.MangaDTO;
import com.example.demoAllTouhou.models.MangaListDTO;
import com.example.demoAllTouhou.services.MangaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/mangas")
public class MangaController {

    @Autowired
    private MangaService mangaService;

    // **Crear un nuevo Manga**
    @PostMapping
    public ResponseEntity<MangaDTO> createManga(@RequestBody MangaDTO mangaDTO) {
        MangaEntity entity = mapToEntity(mangaDTO);
        MangaEntity createdEntity = mangaService.createManga(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToDTO(createdEntity));
    }

    // **Obtener todos los Mangas**
    @GetMapping
    public ResponseEntity<MangaListDTO> getAllMangas() {
        List<MangaEntity> entities = mangaService.getAllMangas();
        List<MangaDTO> dtos = entities.stream().map(this::mapToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(new MangaListDTO(dtos));
    }

    // **Obtener un Manga por ID**
    @GetMapping("/{id}")
    public ResponseEntity<MangaDTO> getMangaById(@PathVariable Long id) {
        MangaEntity entity = mangaService.getMangaById(id);
        return ResponseEntity.ok(mapToDTO(entity));
    }

    // **Actualizar un Manga**
    @PutMapping("/{id}")
    public ResponseEntity<MangaDTO> updateManga(@PathVariable Long id, @RequestBody MangaDTO mangaDTO) {
        MangaEntity entity = mapToEntity(mangaDTO);
        MangaEntity updatedEntity = mangaService.updateManga(id, entity);
        return ResponseEntity.ok(mapToDTO(updatedEntity));
    }

    // **Eliminar un Manga por ID**
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteManga(@PathVariable Long id) {
        mangaService.deleteManga(id);
        return ResponseEntity.noContent().build();
    }

    // **Obtener Mangas por título**
    @GetMapping("/by-title")
    public ResponseEntity<MangaListDTO> getMangasByTitle(@RequestParam String title) {
        List<MangaEntity> entities = mangaService.getMangasByTitle(title);
        List<MangaDTO> dtos = entities.stream().map(this::mapToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(new MangaListDTO(dtos));
    }

    // **Obtener Mangas por autor**
    @GetMapping("/by-author")
    public ResponseEntity<MangaListDTO> getMangasByAuthor(@RequestParam String author) {
        List<MangaEntity> entities = mangaService.getMangasByAuthor(author);
        List<MangaDTO> dtos = entities.stream().map(this::mapToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(new MangaListDTO(dtos));
    }

    // **Mapear de DTO a Entidad**
    private MangaEntity mapToEntity(MangaDTO dto) {
        MangaEntity entity = new MangaEntity();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setAuthor(dto.getAuthor());
        entity.setDescription(dto.getDescription());
        entity.setAccessLink(dto.getAccessLink());
        return entity;
    }

    // **Mapear de Entidad a DTO**
    private MangaDTO mapToDTO(MangaEntity entity) {
        return new MangaDTO(
                entity.getId(),
                entity.getTitle(),
                entity.getAuthor(),
                entity.getDescription(),
                entity.getAccessLink()
        );
    }
}

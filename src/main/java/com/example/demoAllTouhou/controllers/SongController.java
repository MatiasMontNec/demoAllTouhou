package com.example.demoAllTouhou.controllers;

import com.example.demoAllTouhou.entities.SongEntity;
import com.example.demoAllTouhou.models.IdListDTO;
import com.example.demoAllTouhou.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/songs")
public class SongController {

    @Autowired
    private SongService songService;

    // **Crear una nueva Song**
    @PostMapping("/create")
    public ResponseEntity<SongEntity> createSong(@RequestBody SongEntity songEntity) {
        SongEntity createdSong = songService.createSong(songEntity);
        return ResponseEntity.ok(createdSong);
    }

    // **Obtener todas las Songs**
    @GetMapping("/all")
    public ResponseEntity<List<SongEntity>> getAllSongs() {
        List<SongEntity> songs = songService.getAllSongs();
        return ResponseEntity.ok(songs);
    }

    // **Obtener una Song por ID**
    @GetMapping("/{id}")
    public ResponseEntity<SongEntity> getSongById(@PathVariable Long id) {
        SongEntity song = songService.getSongById(id);
        return ResponseEntity.ok(song);
    }

    // **Actualizar una Song**
    @PutMapping("/{id}")
    public ResponseEntity<SongEntity> updateSong(@PathVariable Long id, @RequestBody SongEntity updatedSong) {
        SongEntity song = songService.updateSong(id, updatedSong);
        return ResponseEntity.ok(song);
    }

    // **Eliminar una Song por ID**
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSong(@PathVariable Long id) {
        songService.deleteSong(id);
        return ResponseEntity.noContent().build();
    }

    // **Obtener Songs cuyo título contiene un texto**
    @GetMapping("/search/title")
    public ResponseEntity<List<SongEntity>> getSongsByTitle(@RequestParam String title) {
        List<SongEntity> songs = songService.getSongsByTitle(title);
        return ResponseEntity.ok(songs);
    }

    // **Obtener Songs cuyo artista contiene un texto**
    @GetMapping("/search/artist")
    public ResponseEntity<List<SongEntity>> getSongsByArtist(@RequestParam String artist) {
        List<SongEntity> songs = songService.getSongsByArtist(artist);
        return ResponseEntity.ok(songs);
    }

    // **Obtener Songs por lista de IDs**
    @PostMapping("/by-ids")
    public ResponseEntity<List<SongEntity>> getSongsByIds(@RequestBody IdListDTO idListDTO) {
        List<SongEntity> songs = idListDTO.getIds().stream()
                .map(songService::getSongById)
                .toList();
        return ResponseEntity.ok(songs);
    }
}

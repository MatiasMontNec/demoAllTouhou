package com.example.demoAllTouhou.services;

import com.example.demoAllTouhou.entities.SongEntity;
import com.example.demoAllTouhou.repositories.SongRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {

    private final SongRepository songRepository;

    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    // **Crear una nueva Song**
    public SongEntity createSong(SongEntity songEntity) {
        return songRepository.save(songEntity);
    }

    // **Obtener todas las Songs**
    public List<SongEntity> getAllSongs() {
        return songRepository.findAll();
    }

    // **Obtener una Song por ID**
    public SongEntity getSongById(Long id) {
        return songRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Song not found with id: " + id));
    }

    // **Actualizar una Song**
    public SongEntity updateSong(Long id, SongEntity updatedSong) {
        SongEntity existingSong = songRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Song not found with id: " + id));

        if (updatedSong.getTitle() != null && !updatedSong.getTitle().isEmpty()) existingSong.setTitle(updatedSong.getTitle());
        if (updatedSong.getArtist() != null && !updatedSong.getArtist().isEmpty()) existingSong.setArtist(updatedSong.getArtist());
        if (updatedSong.getDescription() != null && !updatedSong.getDescription().isEmpty()) existingSong.setDescription(updatedSong.getDescription());
        if (updatedSong.getLyrics() != null && !updatedSong.getLyrics().isEmpty()) existingSong.setLyrics(updatedSong.getLyrics());

        return songRepository.save(existingSong);
    }

    // **Eliminar una Song por ID**
    public void deleteSong(Long id) {
        if (!songRepository.existsById(id)) {
            throw new RuntimeException("Song not found with id: " + id);
        }
        songRepository.deleteById(id);
    }

    // **Obtener Songs cuyo título contiene un texto**
    public List<SongEntity> getSongsByTitle(String title) {
        return songRepository.findByTitleContainingIgnoreCase(title);
    }

    // **Obtener Songs cuyo artista contiene un texto**
    public List<SongEntity> getSongsByArtist(String artist) {
        return songRepository.findByArtistContainingIgnoreCase(artist);
    }
}

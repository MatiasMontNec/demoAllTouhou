package com.example.demoAllTouhou.services;

import com.example.demoAllTouhou.entities.GameSongEntity;
import com.example.demoAllTouhou.repositories.GameSongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameSongService {

    @Autowired
    private GameSongRepository gameSongRepository;

    // **Crear un nuevo GameSong**
    public GameSongEntity createGameSong(GameSongEntity gameSongEntity) {
        return gameSongRepository.save(gameSongEntity);
    }

    // **Obtener todos los GameSong**
    public List<GameSongEntity> getAllGameSongs() {
        return gameSongRepository.findAll();
    }

    // **Obtener un GameSong por ID**
    public GameSongEntity getGameSongById(Long id) {
        return gameSongRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("GameSong not found with id: " + id));
    }

    // **Actualizar un GameSong**
    public GameSongEntity updateGameSong(Long id, GameSongEntity updatedGameSong) {
        GameSongEntity existingGameSong = gameSongRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("GameSong not found with id: " + id));

        existingGameSong.setSongId(updatedGameSong.getSongId());
        existingGameSong.setGameId(updatedGameSong.getGameId());

        return gameSongRepository.save(existingGameSong);
    }

    // **Eliminar un GameSong por ID**
    public void deleteGameSong(Long id) {
        if (!gameSongRepository.existsById(id)) {
            throw new RuntimeException("GameSong not found with id: " + id);
        }
        gameSongRepository.deleteById(id);
    }

    // **Obtener todos los GameSong por songId**
    public List<GameSongEntity> getGameSongsBySongId(Long songId) {
        return gameSongRepository.findBySongId(songId);
    }

    // **Obtener todos los GameSong por gameId**
    public List<GameSongEntity> getGameSongsByGameId(Long gameId) {
        return gameSongRepository.findByGameId(gameId);
    }
}

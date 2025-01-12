package com.example.demoAllTouhou.services;

import com.example.demoAllTouhou.entities.CharacterSongEntity;
import com.example.demoAllTouhou.repositories.CharacterSongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterSongService {

    @Autowired
    private CharacterSongRepository characterSongRepository;

    // **Crear una nueva relación Character-Song**
    public CharacterSongEntity createCharacterSong(CharacterSongEntity characterSongEntity) {
        return characterSongRepository.save(characterSongEntity);
    }

    // **Obtener todas las relaciones Character-Song**
    public List<CharacterSongEntity> getAllCharacterSongs() {
        return characterSongRepository.findAll();
    }

    // **Obtener una relación Character-Song por ID**
    public CharacterSongEntity getCharacterSongById(Long id) {
        return characterSongRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Character-Song relation not found with id: " + id));
    }

    // **Actualizar una relación Character-Song**
    public CharacterSongEntity updateCharacterSong(Long id, CharacterSongEntity updatedCharacterSong) {
        CharacterSongEntity existingCharacterSong = characterSongRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Character-Song relation not found with id: " + id));

        existingCharacterSong.setSongId(updatedCharacterSong.getSongId());
        existingCharacterSong.setCharacterId(updatedCharacterSong.getCharacterId());

        return characterSongRepository.save(existingCharacterSong);
    }

    // **Eliminar una relación Character-Song por ID**
    public void deleteCharacterSong(Long id) {
        if (!characterSongRepository.existsById(id)) {
            throw new RuntimeException("Character-Song relation not found with id: " + id);
        }
        characterSongRepository.deleteById(id);
    }

    // **Obtener una lista de Character IDs por Song ID**
    public List<Long> getCharacterIdsBySongId(Long songId) {
        return characterSongRepository.findCharacterIdsBySongId(songId);
    }

    // **Obtener una lista de Song IDs por Character ID**
    public List<Long> getSongIdsByCharacterId(Long characterId) {
        return characterSongRepository.findSongIdsByCharacterId(characterId);
    }
}


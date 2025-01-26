package com.example.demoAllTouhou.services;

import com.example.demoAllTouhou.entities.CharacterEntity;
import com.example.demoAllTouhou.entities.SongEntity;
import com.example.demoAllTouhou.repositories.CharacterRepository;
import com.example.demoAllTouhou.repositories.SongRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongCharactersService {

    private final SongRepository songRepository;
    private final CharacterRepository characterRepository;

    public SongCharactersService(SongRepository songRepository, CharacterRepository characterRepository) {
        this.songRepository = songRepository;
        this.characterRepository = characterRepository;
    }

    // **Obtener la lista de personajes de una canción**
    public List<CharacterEntity> getCharactersBySong(Long songId) {
        SongEntity song = songRepository.findById(songId)
                .orElseThrow(() -> new RuntimeException("Song not found with id: " + songId));
        return song.getCharacters();
    }

    // **Agregar un personaje a la lista de una canción**
    public String addCharacterToSong(Long songId, Long characterId) {
        SongEntity song = songRepository.findById(songId)
                .orElseThrow(() -> new RuntimeException("Song not found with id: " + songId));
        CharacterEntity character = characterRepository.findById(characterId)
                .orElseThrow(() -> new RuntimeException("Character not found with id: " + characterId));

        // Agregar personaje a la canción si no está ya presente
        if (!song.getCharacters().contains(character)) {
            song.getCharacters().add(character);

            // Agregar la canción al personaje (relación bidireccional)
            if (!character.getSongs().contains(song)) {
                character.getSongs().add(song);
            }

            songRepository.save(song); // Persistir la canción
            characterRepository.save(character); // Persistir el personaje

            return "Character added to song.";
        } else {
            throw new IllegalStateException("Character already in song.");
        }
    }

    // **Eliminar un personaje de la lista de una canción**
    public String removeCharacterFromSong(Long songId, Long characterId) {
        SongEntity song = songRepository.findById(songId)
                .orElseThrow(() -> new RuntimeException("Song not found with id: " + songId));

        boolean removed = song.getCharacters().removeIf(character -> character.getId().equals(characterId));

        if (removed) {
            // Eliminar la canción del personaje (relación bidireccional)
            CharacterEntity character = characterRepository.findById(characterId)
                    .orElseThrow(() -> new RuntimeException("Character not found with id: " + characterId));
            character.getSongs().removeIf(s -> s.getId().equals(songId));

            songRepository.save(song); // Persistir la canción
            characterRepository.save(character); // Persistir el personaje

            return "Character removed from song.";
        } else {
            throw new RuntimeException("Character not found in song.");
        }
    }
}
package com.example.demoAllTouhou.services;

import com.example.demoAllTouhou.entities.MangaCharacterEntity;
import com.example.demoAllTouhou.repositories.MangaCharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MangaCharacterService {

    @Autowired
    private MangaCharacterRepository mangaCharacterRepository;

    // **Crear un nuevo MangaCharacter**
    public MangaCharacterEntity createMangaCharacter(MangaCharacterEntity mangaCharacterEntity) {
        return mangaCharacterRepository.save(mangaCharacterEntity);
    }

    // **Obtener todos los MangaCharacter**
    public List<MangaCharacterEntity> getAllMangaCharacters() {
        return mangaCharacterRepository.findAll();
    }

    // **Obtener un MangaCharacter por ID**
    public MangaCharacterEntity getMangaCharacterById(Long id) {
        return mangaCharacterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MangaCharacter not found with id: " + id));
    }

    // **Actualizar un MangaCharacter**
    public MangaCharacterEntity updateMangaCharacter(Long id, MangaCharacterEntity updatedMangaCharacter) {
        MangaCharacterEntity existingMangaCharacter = mangaCharacterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MangaCharacter not found with id: " + id));

        existingMangaCharacter.setMangaId(updatedMangaCharacter.getMangaId());
        existingMangaCharacter.setCharacterId(updatedMangaCharacter.getCharacterId());

        return mangaCharacterRepository.save(existingMangaCharacter);
    }

    // **Eliminar un MangaCharacter por ID**
    public void deleteMangaCharacter(Long id) {
        if (!mangaCharacterRepository.existsById(id)) {
            throw new RuntimeException("MangaCharacter not found with id: " + id);
        }
        mangaCharacterRepository.deleteById(id);
    }

    // **Obtener MangaCharacter por mangaId**
    public List<MangaCharacterEntity> getMangaCharactersByMangaId(Long mangaId) {
        return mangaCharacterRepository.findByMangaId(mangaId);
    }

    // **Obtener MangaCharacter por characterId**
    public List<MangaCharacterEntity> getMangaCharactersByCharacterId(Long characterId) {
        return mangaCharacterRepository.findByCharacterId(characterId);
    }
}

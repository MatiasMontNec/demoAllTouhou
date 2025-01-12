package com.example.demoAllTouhou.services;

import com.example.demoAllTouhou.entities.GameCharacterEntity;
import com.example.demoAllTouhou.repositories.GameCharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameCharacterService {

    @Autowired
    private GameCharacterRepository gameCharacterRepository;

    // **Crear un nuevo GameCharacter**
    public GameCharacterEntity createGameCharacter(GameCharacterEntity gameCharacterEntity) {
        return gameCharacterRepository.save(gameCharacterEntity);
    }

    // **Obtener todos los GameCharacters**
    public List<GameCharacterEntity> getAllGameCharacters() {
        return gameCharacterRepository.findAll();
    }

    // **Obtener un GameCharacter por ID**
    public GameCharacterEntity getGameCharacterById(Long id) {
        return gameCharacterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("GameCharacter not found with id: " + id));
    }

    // **Actualizar un GameCharacter**
    public GameCharacterEntity updateGameCharacter(Long id, GameCharacterEntity updatedGameCharacter) {
        GameCharacterEntity existingGameCharacter = gameCharacterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("GameCharacter not found with id: " + id));

        existingGameCharacter.setCharacterId(updatedGameCharacter.getCharacterId());
        existingGameCharacter.setGameId(updatedGameCharacter.getGameId());

        return gameCharacterRepository.save(existingGameCharacter);
    }

    // **Eliminar un GameCharacter por ID**
    public void deleteGameCharacter(Long id) {
        if (!gameCharacterRepository.existsById(id)) {
            throw new RuntimeException("GameCharacter not found with id: " + id);
        }
        gameCharacterRepository.deleteById(id);
    }

    // **Obtener todos los GameCharacters asociados a un Character ID**
    public List<GameCharacterEntity> getGameCharactersByCharacterId(Long characterId) {
        return gameCharacterRepository.findByCharacterId(characterId);
    }

    // **Obtener todos los GameCharacters asociados a un Game ID**
    public List<GameCharacterEntity> getGameCharactersByGameId(Long gameId) {
        return gameCharacterRepository.findByGameId(gameId);
    }
}

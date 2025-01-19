package com.example.demoAllTouhou.services;

import com.example.demoAllTouhou.entities.GameCharacterEntity;
import com.example.demoAllTouhou.repositories.GameCharacterRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GameCharacterServiceTest {

    @InjectMocks
    private GameCharacterService gameCharacterService;

    @Mock
    private GameCharacterRepository gameCharacterRepository;

    // **Test: Crear un nuevo GameCharacter**
    @Test
    public void whenCreateGameCharacter_thenReturnGameCharacterEntity() {
        GameCharacterEntity character = new GameCharacterEntity();
        character.setCharacterId(1L);
        character.setGameId(2L);

        when(gameCharacterRepository.save(any(GameCharacterEntity.class))).thenReturn(character);

        GameCharacterEntity createdCharacter = gameCharacterService.createGameCharacter(character);

        assertEquals(1L, createdCharacter.getCharacterId());
        assertEquals(2L, createdCharacter.getGameId());
    }

    // **Test: Obtener todos los GameCharacters**
    @Test
    public void whenGetAllGameCharacters_thenReturnListOfGameCharacters() {
        GameCharacterEntity character1 = new GameCharacterEntity();
        character1.setCharacterId(1L);
        character1.setGameId(2L);

        GameCharacterEntity character2 = new GameCharacterEntity();
        character2.setCharacterId(3L);
        character2.setGameId(4L);

        when(gameCharacterRepository.findAll()).thenReturn(Arrays.asList(character1, character2));

        List<GameCharacterEntity> characters = gameCharacterService.getAllGameCharacters();

        assertThat(characters).hasSize(2);
        assertThat(characters).extracting(GameCharacterEntity::getCharacterId)
                .containsExactlyInAnyOrder(1L, 3L);
        assertThat(characters).extracting(GameCharacterEntity::getGameId)
                .containsExactlyInAnyOrder(2L, 4L);
    }

    // **Test: Obtener un GameCharacter por ID**
    @Test
    public void whenGetGameCharacterById_thenReturnGameCharacterEntity() {
        GameCharacterEntity character = new GameCharacterEntity();
        character.setCharacterId(1L);
        character.setGameId(2L);

        when(gameCharacterRepository.findById(1L)).thenReturn(Optional.of(character));

        GameCharacterEntity result = gameCharacterService.getGameCharacterById(1L);

        assertEquals(1L, result.getCharacterId());
        assertEquals(2L, result.getGameId());
    }

    @Test
    public void whenGetGameCharacterById_thenThrowExceptionIfNotFound() {
        when(gameCharacterRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> gameCharacterService.getGameCharacterById(99L));
    }

    // **Test: Actualizar un GameCharacter**
    @Test
    public void whenUpdateGameCharacter_thenReturnUpdatedGameCharacterEntity() {
        GameCharacterEntity existingCharacter = new GameCharacterEntity();
        existingCharacter.setCharacterId(1L);
        existingCharacter.setGameId(2L);

        GameCharacterEntity updatedCharacter = new GameCharacterEntity();
        updatedCharacter.setCharacterId(3L);
        updatedCharacter.setGameId(4L);

        when(gameCharacterRepository.findById(1L)).thenReturn(Optional.of(existingCharacter));
        when(gameCharacterRepository.save(any(GameCharacterEntity.class))).thenReturn(updatedCharacter);

        GameCharacterEntity result = gameCharacterService.updateGameCharacter(1L, updatedCharacter);

        assertEquals(3L, result.getCharacterId());
        assertEquals(4L, result.getGameId());
    }

    @Test
    public void whenUpdateGameCharacter_thenThrowExceptionIfNotFound() {
        when(gameCharacterRepository.findById(99L)).thenReturn(Optional.empty());

        GameCharacterEntity updatedCharacter = new GameCharacterEntity();
        updatedCharacter.setCharacterId(3L);
        updatedCharacter.setGameId(4L);

        assertThrows(RuntimeException.class, () -> gameCharacterService.updateGameCharacter(99L, updatedCharacter));
    }

    // **Test: Eliminar un GameCharacter por ID**
    @Test
    public void whenDeleteGameCharacter_thenDoNothingIfExists() {
        when(gameCharacterRepository.existsById(1L)).thenReturn(true);

        gameCharacterService.deleteGameCharacter(1L);

        verify(gameCharacterRepository, times(1)).deleteById(1L);
    }

    @Test
    public void whenDeleteGameCharacter_thenThrowExceptionIfNotFound() {
        when(gameCharacterRepository.existsById(99L)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> gameCharacterService.deleteGameCharacter(99L));
    }

    // **Test: Obtener todos los GameCharacters asociados a un Character ID**
    @Test
    public void whenGetGameCharactersByCharacterId_thenReturnListOfGameCharacters() {
        Long characterId = 1L;

        GameCharacterEntity character1 = new GameCharacterEntity();
        character1.setCharacterId(characterId);
        character1.setGameId(2L);

        GameCharacterEntity character2 = new GameCharacterEntity();
        character2.setCharacterId(characterId);
        character2.setGameId(3L);

        when(gameCharacterRepository.findByCharacterId(characterId)).thenReturn(Arrays.asList(character1, character2));

        List<GameCharacterEntity> characters = gameCharacterService.getGameCharactersByCharacterId(characterId);

        assertThat(characters).hasSize(2);
        assertThat(characters).extracting(GameCharacterEntity::getGameId)
                .containsExactlyInAnyOrder(2L, 3L);
    }

    // **Test: Obtener todos los GameCharacters asociados a un Game ID**
    @Test
    public void whenGetGameCharactersByGameId_thenReturnListOfGameCharacters() {
        Long gameId = 2L;

        GameCharacterEntity character1 = new GameCharacterEntity();
        character1.setCharacterId(1L);
        character1.setGameId(gameId);

        GameCharacterEntity character2 = new GameCharacterEntity();
        character2.setCharacterId(3L);
        character2.setGameId(gameId);

        when(gameCharacterRepository.findByGameId(gameId)).thenReturn(Arrays.asList(character1, character2));

        List<GameCharacterEntity> characters = gameCharacterService.getGameCharactersByGameId(gameId);

        assertThat(characters).hasSize(2);
        assertThat(characters).extracting(GameCharacterEntity::getCharacterId)
                .containsExactlyInAnyOrder(1L, 3L);
    }
}

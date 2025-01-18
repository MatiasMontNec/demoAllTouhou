package com.example.demoAllTouhou.services;

import com.example.demoAllTouhou.entities.CharacterSongEntity;
import com.example.demoAllTouhou.repositories.CharacterSongRepository;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) // Extiende con Mockito
public class CharacterSongServiceTest {

    @InjectMocks
    private CharacterSongService characterSongService;  // Inyecta los mocks en el servicio

    @Mock
    private CharacterSongRepository characterSongRepository;  // Mock del repositorio

    // **Test: Crear una nueva relación Character-Song**
    @Test
    public void whenCreateCharacterSong_thenReturnCharacterSongEntity() {
        CharacterSongEntity characterSong = new CharacterSongEntity();
        characterSong.setSongId(1L);
        characterSong.setCharacterId(2L);

        when(characterSongRepository.save(any(CharacterSongEntity.class))).thenReturn(characterSong);

        CharacterSongEntity createdCharacterSong = characterSongService.createCharacterSong(characterSong);

        assertEquals(1L, createdCharacterSong.getSongId());
        assertEquals(2L, createdCharacterSong.getCharacterId());
    }

    // **Test: Obtener todas las relaciones Character-Song**
    @Test
    public void whenGetAllCharacterSongs_thenReturnListOfCharacterSongs() {
        CharacterSongEntity characterSong1 = new CharacterSongEntity();
        characterSong1.setSongId(1L);
        characterSong1.setCharacterId(2L);
        CharacterSongEntity characterSong2 = new CharacterSongEntity();
        characterSong2.setSongId(2L);
        characterSong2.setCharacterId(3L);

        when(characterSongRepository.findAll()).thenReturn(Arrays.asList(characterSong1, characterSong2));

        List<CharacterSongEntity> characterSongs = characterSongService.getAllCharacterSongs();

        assertThat(characterSongs).hasSize(2);
        assertThat(characterSongs).extracting(CharacterSongEntity::getSongId)
                .containsExactlyInAnyOrder(1L, 2L);
        assertThat(characterSongs).extracting(CharacterSongEntity::getCharacterId)
                .containsExactlyInAnyOrder(2L, 3L);
    }

    // **Test: Obtener una relación Character-Song por ID**
    @Test
    public void whenGetCharacterSongById_thenReturnCharacterSongEntity() {
        CharacterSongEntity characterSong = new CharacterSongEntity();
        characterSong.setSongId(1L);
        characterSong.setCharacterId(2L);
        characterSong.setId(1L);

        when(characterSongRepository.findById(1L)).thenReturn(Optional.of(characterSong));

        CharacterSongEntity result = characterSongService.getCharacterSongById(1L);

        assertEquals(1L, result.getSongId());
        assertEquals(2L, result.getCharacterId());
    }

    @Test
    public void whenGetCharacterSongById_thenThrowExceptionIfNotFound() {
        when(characterSongRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> characterSongService.getCharacterSongById(99L));
    }

    // **Test: Actualizar una relación Character-Song existente**
    @Test
    public void whenUpdateCharacterSong_thenReturnUpdatedCharacterSong() {
        CharacterSongEntity existingCharacterSong = new CharacterSongEntity();
        existingCharacterSong.setId(1L);
        existingCharacterSong.setSongId(1L);
        existingCharacterSong.setCharacterId(2L);

        CharacterSongEntity updatedCharacterSong = new CharacterSongEntity();
        updatedCharacterSong.setSongId(2L);
        updatedCharacterSong.setCharacterId(3L);

        when(characterSongRepository.findById(1L)).thenReturn(Optional.of(existingCharacterSong));
        when(characterSongRepository.save(any(CharacterSongEntity.class))).thenReturn(updatedCharacterSong);

        CharacterSongEntity result = characterSongService.updateCharacterSong(1L, updatedCharacterSong);

        assertEquals(2L, result.getSongId());
        assertEquals(3L, result.getCharacterId());
    }

    @Test
    public void whenUpdateCharacterSong_thenThrowExceptionIfNotFound() {
        when(characterSongRepository.findById(99L)).thenReturn(Optional.empty());

        CharacterSongEntity updatedCharacterSong = new CharacterSongEntity();
        updatedCharacterSong.setSongId(2L);
        updatedCharacterSong.setCharacterId(3L);

        assertThrows(RuntimeException.class, () -> characterSongService.updateCharacterSong(99L, updatedCharacterSong));
    }

    // **Test: Eliminar una relación Character-Song por ID**
    @Test
    public void whenDeleteCharacterSong_thenDoNothingIfExists() {
        when(characterSongRepository.existsById(1L)).thenReturn(true);

        characterSongService.deleteCharacterSong(1L);
    }

    @Test
    public void whenDeleteCharacterSong_thenThrowExceptionIfNotFound() {
        when(characterSongRepository.existsById(99L)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> characterSongService.deleteCharacterSong(99L));
    }

    // **Test: Obtener una lista de Character IDs por Song ID**
    @Test
    public void whenGetCharacterIdsBySongId_thenReturnList() {
        Long songId = 1L;
        Long characterId1 = 2L;
        Long characterId2 = 3L;

        when(characterSongRepository.findCharacterIdsBySongId(songId)).thenReturn(Arrays.asList(characterId1, characterId2));

        List<Long> characterIds = characterSongService.getCharacterIdsBySongId(songId);

        assertThat(characterIds).hasSize(2);
        assertThat(characterIds).containsExactlyInAnyOrder(characterId1, characterId2);
    }

    // **Test: Obtener una lista de Song IDs por Character ID**
    @Test
    public void whenGetSongIdsByCharacterId_thenReturnList() {
        Long characterId = 2L;
        Long songId1 = 1L;
        Long songId2 = 2L;

        when(characterSongRepository.findSongIdsByCharacterId(characterId)).thenReturn(Arrays.asList(songId1, songId2));

        List<Long> songIds = characterSongService.getSongIdsByCharacterId(characterId);

        assertThat(songIds).hasSize(2);
        assertThat(songIds).containsExactlyInAnyOrder(songId1, songId2);
    }

}

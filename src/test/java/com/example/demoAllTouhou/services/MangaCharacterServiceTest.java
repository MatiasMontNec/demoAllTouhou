package com.example.demoAllTouhou.services;

import com.example.demoAllTouhou.entities.MangaCharacterEntity;
import com.example.demoAllTouhou.repositories.MangaCharacterRepository;
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
public class MangaCharacterServiceTest {

    @InjectMocks
    private MangaCharacterService mangaCharacterService;

    @Mock
    private MangaCharacterRepository mangaCharacterRepository;

    // **Test: Crear un nuevo MangaCharacter**
    @Test
    public void whenCreateMangaCharacter_thenReturnMangaCharacterEntity() {
        MangaCharacterEntity mangaCharacter = new MangaCharacterEntity();
        mangaCharacter.setMangaId(1L);
        mangaCharacter.setCharacterId(2L);

        when(mangaCharacterRepository.save(any(MangaCharacterEntity.class))).thenReturn(mangaCharacter);

        MangaCharacterEntity createdMangaCharacter = mangaCharacterService.createMangaCharacter(mangaCharacter);

        assertEquals(1L, createdMangaCharacter.getMangaId());
        assertEquals(2L, createdMangaCharacter.getCharacterId());
    }

    // **Test: Obtener todos los MangaCharacters**
    @Test
    public void whenGetAllMangaCharacters_thenReturnListOfMangaCharacters() {
        MangaCharacterEntity mangaCharacter1 = new MangaCharacterEntity();
        mangaCharacter1.setMangaId(1L);

        MangaCharacterEntity mangaCharacter2 = new MangaCharacterEntity();
        mangaCharacter2.setMangaId(2L);

        when(mangaCharacterRepository.findAll()).thenReturn(Arrays.asList(mangaCharacter1, mangaCharacter2));

        List<MangaCharacterEntity> mangaCharacters = mangaCharacterService.getAllMangaCharacters();

        assertThat(mangaCharacters).hasSize(2);
        assertThat(mangaCharacters).extracting(MangaCharacterEntity::getMangaId)
                .containsExactlyInAnyOrder(1L, 2L);
    }

    // **Test: Obtener un MangaCharacter por ID**
    @Test
    public void whenGetMangaCharacterById_thenReturnMangaCharacterEntity() {
        MangaCharacterEntity mangaCharacter = new MangaCharacterEntity();
        mangaCharacter.setId(1L);
        mangaCharacter.setMangaId(1L);

        when(mangaCharacterRepository.findById(1L)).thenReturn(Optional.of(mangaCharacter));

        MangaCharacterEntity result = mangaCharacterService.getMangaCharacterById(1L);

        assertEquals(1L, result.getId());
        assertEquals(1L, result.getMangaId());
    }

    @Test
    public void whenGetMangaCharacterById_thenThrowExceptionIfNotFound() {
        when(mangaCharacterRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> mangaCharacterService.getMangaCharacterById(99L));
    }

    // **Test: Actualizar un MangaCharacter**
    @Test
    public void whenUpdateMangaCharacter_thenReturnUpdatedMangaCharacterEntity() {
        MangaCharacterEntity existingMangaCharacter = new MangaCharacterEntity();
        existingMangaCharacter.setId(1L);
        existingMangaCharacter.setMangaId(1L);

        MangaCharacterEntity updatedMangaCharacter = new MangaCharacterEntity();
        updatedMangaCharacter.setMangaId(2L);
        updatedMangaCharacter.setCharacterId(3L);

        when(mangaCharacterRepository.findById(1L)).thenReturn(Optional.of(existingMangaCharacter));
        when(mangaCharacterRepository.save(any(MangaCharacterEntity.class))).thenReturn(updatedMangaCharacter);

        MangaCharacterEntity result = mangaCharacterService.updateMangaCharacter(1L, updatedMangaCharacter);

        assertEquals(2L, result.getMangaId());
        assertEquals(3L, result.getCharacterId());
    }

    @Test
    public void whenUpdateMangaCharacter_thenThrowExceptionIfNotFound() {
        when(mangaCharacterRepository.findById(99L)).thenReturn(Optional.empty());

        MangaCharacterEntity updatedMangaCharacter = new MangaCharacterEntity();
        updatedMangaCharacter.setMangaId(2L);

        assertThrows(RuntimeException.class, () -> mangaCharacterService.updateMangaCharacter(99L, updatedMangaCharacter));
    }

    // **Test: Eliminar un MangaCharacter por ID**
    @Test
    public void whenDeleteMangaCharacter_thenDoNothingIfExists() {
        when(mangaCharacterRepository.existsById(1L)).thenReturn(true);

        mangaCharacterService.deleteMangaCharacter(1L);

        verify(mangaCharacterRepository, times(1)).deleteById(1L);
    }

    @Test
    public void whenDeleteMangaCharacter_thenThrowExceptionIfNotFound() {
        when(mangaCharacterRepository.existsById(99L)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> mangaCharacterService.deleteMangaCharacter(99L));
    }

    // **Test: Obtener MangaCharacter por mangaId**
    @Test
    public void whenGetMangaCharactersByMangaId_thenReturnListOfMangaCharacters() {
        Long mangaId = 1L;

        MangaCharacterEntity mangaCharacter1 = new MangaCharacterEntity();
        mangaCharacter1.setMangaId(mangaId);

        MangaCharacterEntity mangaCharacter2 = new MangaCharacterEntity();
        mangaCharacter2.setMangaId(mangaId);

        when(mangaCharacterRepository.findByMangaId(mangaId)).thenReturn(Arrays.asList(mangaCharacter1, mangaCharacter2));

        List<MangaCharacterEntity> mangaCharacters = mangaCharacterService.getMangaCharactersByMangaId(mangaId);

        assertThat(mangaCharacters).hasSize(2);
    }

    // **Test: Obtener MangaCharacter por characterId**
    @Test
    public void whenGetMangaCharactersByCharacterId_thenReturnListOfMangaCharacters() {
        Long characterId = 1L;

        MangaCharacterEntity mangaCharacter1 = new MangaCharacterEntity();
        mangaCharacter1.setCharacterId(characterId);

        MangaCharacterEntity mangaCharacter2 = new MangaCharacterEntity();
        mangaCharacter2.setCharacterId(characterId);

        when(mangaCharacterRepository.findByCharacterId(characterId)).thenReturn(Arrays.asList(mangaCharacter1, mangaCharacter2));

        List<MangaCharacterEntity> mangaCharacters = mangaCharacterService.getMangaCharactersByCharacterId(characterId);

        assertThat(mangaCharacters).hasSize(2);
    }
}

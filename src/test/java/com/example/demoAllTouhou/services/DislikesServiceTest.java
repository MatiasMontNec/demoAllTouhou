package com.example.demoAllTouhou.services;

import com.example.demoAllTouhou.entities.DislikesEntity;
import com.example.demoAllTouhou.repositories.DislikesRepository;
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
public class DislikesServiceTest {

    @InjectMocks
    private DislikesService dislikesService;

    @Mock
    private DislikesRepository dislikesRepository;

    // **Test: Crear un nuevo Dislike**
    @Test
    public void whenCreateDislike_thenReturnDislikesEntity() {
        DislikesEntity dislike = new DislikesEntity();
        dislike.setName("Dislike Name");
        dislike.setDescription("Dislike Description");

        when(dislikesRepository.save(any(DislikesEntity.class))).thenReturn(dislike);

        DislikesEntity createdDislike = dislikesService.createDislike(dislike);

        assertEquals("Dislike Name", createdDislike.getName());
        assertEquals("Dislike Description", createdDislike.getDescription());
    }

    // **Test: Obtener todos los Dislikes**
    @Test
    public void whenGetAllDislikes_thenReturnListOfDislikes() {
        DislikesEntity dislike1 = new DislikesEntity();
        dislike1.setName("Dislike 1");

        DislikesEntity dislike2 = new DislikesEntity();
        dislike2.setName("Dislike 2");

        when(dislikesRepository.findAll()).thenReturn(Arrays.asList(dislike1, dislike2));

        List<DislikesEntity> dislikes = dislikesService.getAllDislikes();

        assertThat(dislikes).hasSize(2);
        assertThat(dislikes).extracting(DislikesEntity::getName)
                .containsExactlyInAnyOrder("Dislike 1", "Dislike 2");
    }

    // **Test: Obtener un Dislike por ID**
    @Test
    public void whenGetDislikeById_thenReturnDislikesEntity() {
        DislikesEntity dislike = new DislikesEntity();
        dislike.setId(1L);
        dislike.setName("Dislike Name");

        when(dislikesRepository.findById(1L)).thenReturn(Optional.of(dislike));

        DislikesEntity result = dislikesService.getDislikeById(1L);

        assertEquals(1L, result.getId());
        assertEquals("Dislike Name", result.getName());
    }

    @Test
    public void whenGetDislikeById_thenThrowExceptionIfNotFound() {
        when(dislikesRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> dislikesService.getDislikeById(99L));
    }

    // **Test: Actualizar un Dislike**
    @Test
    public void whenUpdateDislike_thenReturnUpdatedDislikesEntity() {
        DislikesEntity existingDislike = new DislikesEntity();
        existingDislike.setId(1L);
        existingDislike.setName("Old Name");
        existingDislike.setDescription("Old Description");

        DislikesEntity updatedDislike = new DislikesEntity();
        updatedDislike.setName("New Name");
        updatedDislike.setDescription("New Description");

        when(dislikesRepository.findById(1L)).thenReturn(Optional.of(existingDislike));
        when(dislikesRepository.save(any(DislikesEntity.class))).thenReturn(updatedDislike);

        DislikesEntity result = dislikesService.updateDislike(1L, updatedDislike);

        assertEquals("New Name", result.getName());
        assertEquals("New Description", result.getDescription());
    }

    @Test
    public void whenUpdateDislike_thenThrowExceptionIfNotFound() {
        when(dislikesRepository.findById(99L)).thenReturn(Optional.empty());

        DislikesEntity updatedDislike = new DislikesEntity();
        updatedDislike.setName("New Name");
        updatedDislike.setDescription("New Description");

        assertThrows(RuntimeException.class, () -> dislikesService.updateDislike(99L, updatedDislike));
    }

    // **Test: Eliminar un Dislike por ID**
    @Test
    public void whenDeleteDislike_thenDoNothingIfExists() {
        when(dislikesRepository.existsById(1L)).thenReturn(true);

        dislikesService.deleteDislike(1L);

        verify(dislikesRepository, times(1)).deleteById(1L);
    }

    @Test
    public void whenDeleteDislike_thenThrowExceptionIfNotFound() {
        when(dislikesRepository.existsById(99L)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> dislikesService.deleteDislike(99L));
    }

    // **Test: Obtener todos los Dislikes asociados a un Character ID**
    @Test
    public void whenGetDislikesByCharacterId_thenReturnListOfDislikes() {
        Long characterId = 1L;

        DislikesEntity dislike1 = new DislikesEntity();
        dislike1.setName("Dislike 1");

        DislikesEntity dislike2 = new DislikesEntity();
        dislike2.setName("Dislike 2");

        when(dislikesRepository.findByCharacters_Id(characterId)).thenReturn(Arrays.asList(dislike1, dislike2));

        List<DislikesEntity> dislikes = dislikesService.getDislikesByCharacterId(characterId);

        assertThat(dislikes).hasSize(2);
        assertThat(dislikes).extracting(DislikesEntity::getName)
                .containsExactlyInAnyOrder("Dislike 1", "Dislike 2");
    }
}

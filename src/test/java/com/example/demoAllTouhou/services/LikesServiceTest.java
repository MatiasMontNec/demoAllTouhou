package com.example.demoAllTouhou.services;

import com.example.demoAllTouhou.entities.LikesEntity;
import com.example.demoAllTouhou.repositories.LikesRepository;
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
public class LikesServiceTest {

    @InjectMocks
    private LikesService likesService;

    @Mock
    private LikesRepository likesRepository;

    // **Test: Crear un nuevo Like**
    @Test
    public void whenCreateLike_thenReturnLikesEntity() {
        LikesEntity like = new LikesEntity();
        like.setName("Like Name");
        like.setDescription("Like Description");

        when(likesRepository.save(any(LikesEntity.class))).thenReturn(like);

        LikesEntity createdLike = likesService.createLike(like);

        assertEquals("Like Name", createdLike.getName());
        assertEquals("Like Description", createdLike.getDescription());
    }

    // **Test: Obtener todos los Likes**
    @Test
    public void whenGetAllLikes_thenReturnListOfLikes() {
        LikesEntity like1 = new LikesEntity();
        like1.setName("Like 1");

        LikesEntity like2 = new LikesEntity();
        like2.setName("Like 2");

        when(likesRepository.findAll()).thenReturn(Arrays.asList(like1, like2));

        List<LikesEntity> likes = likesService.getAllLikes();

        assertThat(likes).hasSize(2);
        assertThat(likes).extracting(LikesEntity::getName)
                .containsExactlyInAnyOrder("Like 1", "Like 2");
    }

    // **Test: Obtener un Like por ID**
    @Test
    public void whenGetLikeById_thenReturnLikesEntity() {
        LikesEntity like = new LikesEntity();
        like.setId(1L);
        like.setName("Like Name");

        when(likesRepository.findById(1L)).thenReturn(Optional.of(like));

        LikesEntity result = likesService.getLikeById(1L);

        assertEquals(1L, result.getId());
        assertEquals("Like Name", result.getName());
    }

    @Test
    public void whenGetLikeById_thenThrowExceptionIfNotFound() {
        when(likesRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> likesService.getLikeById(99L));
    }

    // **Test: Actualizar un Like**
    @Test
    public void whenUpdateLike_thenReturnUpdatedLikesEntity() {
        LikesEntity existingLike = new LikesEntity();
        existingLike.setId(1L);
        existingLike.setName("Old Name");
        existingLike.setDescription("Old Description");

        LikesEntity updatedLike = new LikesEntity();
        updatedLike.setName("New Name");
        updatedLike.setDescription("New Description");

        when(likesRepository.findById(1L)).thenReturn(Optional.of(existingLike));
        when(likesRepository.save(any(LikesEntity.class))).thenReturn(updatedLike);

        LikesEntity result = likesService.updateLike(1L, updatedLike);

        assertEquals("New Name", result.getName());
        assertEquals("New Description", result.getDescription());
    }

    @Test
    public void whenUpdateLike_thenThrowExceptionIfNotFound() {
        when(likesRepository.findById(99L)).thenReturn(Optional.empty());

        LikesEntity updatedLike = new LikesEntity();
        updatedLike.setName("New Name");
        updatedLike.setDescription("New Description");

        assertThrows(RuntimeException.class, () -> likesService.updateLike(99L, updatedLike));
    }

    // **Test: Eliminar un Like por ID**
    @Test
    public void whenDeleteLike_thenDoNothingIfExists() {
        when(likesRepository.existsById(1L)).thenReturn(true);

        likesService.deleteLike(1L);

        verify(likesRepository, times(1)).deleteById(1L);
    }

    @Test
    public void whenDeleteLike_thenThrowExceptionIfNotFound() {
        when(likesRepository.existsById(99L)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> likesService.deleteLike(99L));
    }

    // **Test: Buscar likes por parte del nombre**
    @Test
    public void whenGetLikesByNameContains_thenReturnListOfLikes() {
        LikesEntity like1 = new LikesEntity();
        like1.setName("Touhou Like 1");

        LikesEntity like2 = new LikesEntity();
        like2.setName("Touhou Like 2");

        when(likesRepository.findByNameContainingIgnoreCase("Touhou")).thenReturn(Arrays.asList(like1, like2));

        List<LikesEntity> likes = likesService.getLikesByNameContains("Touhou");

        assertThat(likes).hasSize(2);
        assertThat(likes).extracting(LikesEntity::getName)
                .containsExactlyInAnyOrder("Touhou Like 1", "Touhou Like 2");
    }

    // **Test: Obtener likes por Character ID**
    @Test
    public void whenGetLikesByCharacterId_thenReturnListOfLikes() {
        Long characterId = 1L;

        LikesEntity like1 = new LikesEntity();
        like1.setName("Like 1");

        LikesEntity like2 = new LikesEntity();
        like2.setName("Like 2");

        when(likesRepository.findByCharacters_Id(characterId)).thenReturn(Arrays.asList(like1, like2));

        List<LikesEntity> likes = likesService.getLikesByCharacterId(characterId);

        assertThat(likes).hasSize(2);
        assertThat(likes).extracting(LikesEntity::getName)
                .containsExactlyInAnyOrder("Like 1", "Like 2");
    }
}

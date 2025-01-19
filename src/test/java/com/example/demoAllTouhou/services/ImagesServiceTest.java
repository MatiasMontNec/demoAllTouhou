package com.example.demoAllTouhou.services;

import com.example.demoAllTouhou.entities.ImagesEntity;
import com.example.demoAllTouhou.repositories.ImagesRepository;
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
public class ImagesServiceTest {

    @InjectMocks
    private ImagesService imagesService;

    @Mock
    private ImagesRepository imagesRepository;

    // **Test: Crear una nueva imagen**
    @Test
    public void whenCreateImage_thenReturnImagesEntity() {
        ImagesEntity image = new ImagesEntity();
        image.setName("Image Name");
        image.setDescription("Image Description");
        image.setCharacterId(1L);

        when(imagesRepository.save(any(ImagesEntity.class))).thenReturn(image);

        ImagesEntity createdImage = imagesService.createImage(image);

        assertEquals("Image Name", createdImage.getName());
        assertEquals("Image Description", createdImage.getDescription());
        assertEquals(1L, createdImage.getCharacterId());
    }

    // **Test: Obtener todas las imágenes**
    @Test
    public void whenGetAllImages_thenReturnListOfImages() {
        ImagesEntity image1 = new ImagesEntity();
        image1.setName("Image 1");
        image1.setCharacterId(1L);

        ImagesEntity image2 = new ImagesEntity();
        image2.setName("Image 2");
        image2.setCharacterId(2L);

        when(imagesRepository.findAll()).thenReturn(Arrays.asList(image1, image2));

        List<ImagesEntity> images = imagesService.getAllImages();

        assertThat(images).hasSize(2);
        assertThat(images).extracting(ImagesEntity::getName)
                .containsExactlyInAnyOrder("Image 1", "Image 2");
    }

    // **Test: Obtener una imagen por ID**
    @Test
    public void whenGetImageById_thenReturnImagesEntity() {
        ImagesEntity image = new ImagesEntity();
        image.setId(1L);
        image.setName("Image Name");

        when(imagesRepository.findById(1L)).thenReturn(Optional.of(image));

        ImagesEntity result = imagesService.getImageById(1L);

        assertEquals(1L, result.getId());
        assertEquals("Image Name", result.getName());
    }

    @Test
    public void whenGetImageById_thenThrowExceptionIfNotFound() {
        when(imagesRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> imagesService.getImageById(99L));
    }

    // **Test: Actualizar una imagen**
    @Test
    public void whenUpdateImage_thenReturnUpdatedImagesEntity() {
        ImagesEntity existingImage = new ImagesEntity();
        existingImage.setId(1L);
        existingImage.setName("Old Name");
        existingImage.setCharacterId(1L);

        ImagesEntity updatedImage = new ImagesEntity();
        updatedImage.setName("New Name");
        updatedImage.setCharacterId(2L);

        when(imagesRepository.findById(1L)).thenReturn(Optional.of(existingImage));
        when(imagesRepository.save(any(ImagesEntity.class))).thenReturn(updatedImage);

        ImagesEntity result = imagesService.updateImage(1L, updatedImage);

        assertEquals("New Name", result.getName());
        assertEquals(2L, result.getCharacterId());
    }

    @Test
    public void whenUpdateImage_thenThrowExceptionIfNotFound() {
        when(imagesRepository.findById(99L)).thenReturn(Optional.empty());

        ImagesEntity updatedImage = new ImagesEntity();
        updatedImage.setName("New Name");

        assertThrows(RuntimeException.class, () -> imagesService.updateImage(99L, updatedImage));
    }

    // **Test: Eliminar una imagen por ID**
    @Test
    public void whenDeleteImage_thenDoNothingIfExists() {
        when(imagesRepository.existsById(1L)).thenReturn(true);

        imagesService.deleteImage(1L);

        verify(imagesRepository, times(1)).deleteById(1L);
    }

    @Test
    public void whenDeleteImage_thenThrowExceptionIfNotFound() {
        when(imagesRepository.existsById(99L)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> imagesService.deleteImage(99L));
    }

    // **Test: Buscar imágenes por parte del nombre**
    @Test
    public void whenGetImagesByNameContains_thenReturnListOfImages() {
        ImagesEntity image1 = new ImagesEntity();
        image1.setName("Test Image 1");

        ImagesEntity image2 = new ImagesEntity();
        image2.setName("Another Test Image");

        when(imagesRepository.findByNameContainingIgnoreCase("Test"))
                .thenReturn(Arrays.asList(image1, image2));

        List<ImagesEntity> images = imagesService.getImagesByNameContains("Test");

        assertThat(images).hasSize(2);
        assertThat(images).extracting(ImagesEntity::getName)
                .containsExactlyInAnyOrder("Test Image 1", "Another Test Image");
    }

    // **Test: Obtener imágenes por characterId**
    @Test
    public void whenGetImagesByCharacterId_thenReturnListOfImages() {
        Long characterId = 1L;

        ImagesEntity image1 = new ImagesEntity();
        image1.setCharacterId(characterId);

        ImagesEntity image2 = new ImagesEntity();
        image2.setCharacterId(characterId);

        when(imagesRepository.findByCharacterId(characterId))
                .thenReturn(Arrays.asList(image1, image2));

        List<ImagesEntity> images = imagesService.getImagesByCharacterId(characterId);

        assertThat(images).hasSize(2);
    }

    // **Test: Obtener imágenes por gameId**
    @Test
    public void whenGetImagesByGameId_thenReturnListOfImages() {
        Long gameId = 1L;

        ImagesEntity image1 = new ImagesEntity();
        image1.setGameId(gameId);

        ImagesEntity image2 = new ImagesEntity();
        image2.setGameId(gameId);

        when(imagesRepository.findByGameId(gameId))
                .thenReturn(Arrays.asList(image1, image2));

        List<ImagesEntity> images = imagesService.getImagesByGameId(gameId);

        assertThat(images).hasSize(2);
    }

    // **Test: Obtener imágenes por mercancyId**
    @Test
    public void whenGetImagesByMercancyId_thenReturnListOfImages() {
        Long mercancyId = 1L;

        ImagesEntity image1 = new ImagesEntity();
        image1.setMercancyId(mercancyId);

        ImagesEntity image2 = new ImagesEntity();
        image2.setMercancyId(mercancyId);

        when(imagesRepository.findByMercancyId(mercancyId))
                .thenReturn(Arrays.asList(image1, image2));

        List<ImagesEntity> images = imagesService.getImagesByMercancyId(mercancyId);

        assertThat(images).hasSize(2);
    }
}

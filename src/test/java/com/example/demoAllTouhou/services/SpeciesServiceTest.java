package com.example.demoAllTouhou.services;

import com.example.demoAllTouhou.entities.SpeciesEntity;
import com.example.demoAllTouhou.repositories.SpeciesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SpeciesServiceTest {

    @InjectMocks
    private SpeciesService speciesService;

    @Mock
    private SpeciesRepository speciesRepository;

    // **Test: Crear una nueva especie**
    @Test
    public void whenCreateSpecie_thenReturnSpeciesEntity() {
        SpeciesEntity image = new SpeciesEntity();
        image.setName("Specie Name");
        image.setDescription("Specie Description");

        when(speciesRepository.save(any(SpeciesEntity.class))).thenReturn(image);

        SpeciesEntity createdSpecie = speciesService.createSpecies(image);

        assertEquals("Specie Name", createdSpecie.getName());
        assertEquals("Specie Description", createdSpecie.getDescription());
    }

    // **Test: Obtener todas las especies**
    @Test
    public void whenGetAllSpecies_thenReturnListOfSpecies() {
        SpeciesEntity image1 = new SpeciesEntity();
        image1.setName("Specie 1");

        SpeciesEntity image2 = new SpeciesEntity();
        image2.setName("Specie 2");

        when(speciesRepository.findAll()).thenReturn(Arrays.asList(image1, image2));

        List<SpeciesEntity> species = speciesService.getAllSpecies();

        assertThat(species).hasSize(2);
        assertThat(species).extracting(SpeciesEntity::getName)
                .containsExactlyInAnyOrder("Specie 1", "Specie 2");
    }

    // **Test: Obtener una especie por ID**
    @Test
    public void whenGetSpecieById_thenReturnSpeciesEntity() {
        SpeciesEntity image = new SpeciesEntity();
        image.setId(1L);
        image.setName("Specie Name");

        when(speciesRepository.findById(1L)).thenReturn(Optional.of(image));

        SpeciesEntity result = speciesService.getSpeciesById(1L);

        assertEquals(1L, result.getId());
        assertEquals("Specie Name", result.getName());
    }

    @Test
    public void whenGetSpecieById_thenThrowExceptionIfNotFound() {
        when(speciesRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> speciesService.getSpeciesById(99L));
    }

    // **Test: Actualizar una especie**
    @Test
    public void whenUpdateSpecie_thenReturnUpdatedSpeciesEntity() {
        SpeciesEntity existingSpecie = new SpeciesEntity();
        existingSpecie.setId(1L);
        existingSpecie.setName("Old Name");

        SpeciesEntity updatedSpecie = new SpeciesEntity();
        updatedSpecie.setName("New Name");

        when(speciesRepository.findById(1L)).thenReturn(Optional.of(existingSpecie));
        when(speciesRepository.save(any(SpeciesEntity.class))).thenReturn(updatedSpecie);

        SpeciesEntity result = speciesService.updateSpecies(1L, updatedSpecie);

        assertEquals("New Name", result.getName());
    }

    @Test
    public void whenUpdateSpecie_thenThrowExceptionIfNotFound() {
        when(speciesRepository.findById(99L)).thenReturn(Optional.empty());

        SpeciesEntity updatedSpecie = new SpeciesEntity();
        updatedSpecie.setName("New Name");

        assertThrows(RuntimeException.class, () -> speciesService.updateSpecies(99L, updatedSpecie));
    }

    // **Test: Eliminar una especie por ID**
    @Test
    public void whenDeleteSpecie_thenDoNothingIfExists() {
        when(speciesRepository.existsById(1L)).thenReturn(true);

        speciesService.deleteSpecies(1L);

        verify(speciesRepository, times(1)).deleteById(1L);
    }

    @Test
    public void whenDeleteSpecie_thenThrowExceptionIfNotFound() {
        when(speciesRepository.existsById(99L)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> speciesService.deleteSpecies(99L));
    }
}

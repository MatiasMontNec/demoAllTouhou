package com.example.demoAllTouhou.services;

import com.example.demoAllTouhou.entities.MercancyEntity;
import com.example.demoAllTouhou.repositories.MercancyRepository;
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

@ExtendWith(MockitoExtension.class)
public class MercancyServiceTest {

    @InjectMocks
    private MercancyService mercancyService;

    @Mock
    private MercancyRepository mercancyRepository;

    // **Test: Crear una nueva Mercancía**
    @Test
    public void whenCreateMercancy_thenReturnMercancyEntity() {
        MercancyEntity mercancy = new MercancyEntity();
        mercancy.setName("New Mercancy");
        mercancy.setPrice(100);

        when(mercancyRepository.save(any(MercancyEntity.class))).thenReturn(mercancy);

        MercancyEntity createdMercancy = mercancyService.createMercancy(mercancy);

        assertEquals("New Mercancy", createdMercancy.getName());
        assertEquals(100, createdMercancy.getPrice());
    }

    // **Test: Obtener todas las Mercancías**
    @Test
    public void whenGetAllMercancy_thenReturnListOfMercancy() {
        MercancyEntity mercancy1 = new MercancyEntity();
        mercancy1.setName("Mercancy 1");
        MercancyEntity mercancy2 = new MercancyEntity();
        mercancy2.setName("Mercancy 2");

        when(mercancyRepository.findAll()).thenReturn(Arrays.asList(mercancy1, mercancy2));

        List<MercancyEntity> mercancies = mercancyService.getAllMercancy();

        assertThat(mercancies).hasSize(2);
        assertThat(mercancies).extracting(MercancyEntity::getName)
                .containsExactlyInAnyOrder("Mercancy 1", "Mercancy 2");
    }

    // **Test: Obtener Mercancía por ID**
    @Test
    public void whenGetMercancyById_thenReturnMercancyEntity() {
        MercancyEntity mercancy = new MercancyEntity();
        mercancy.setId(1L);
        mercancy.setName("Mercancy Name");

        when(mercancyRepository.findById(1L)).thenReturn(Optional.of(mercancy));

        MercancyEntity result = mercancyService.getMercancyById(1L);

        assertEquals(1L, result.getId());
        assertEquals("Mercancy Name", result.getName());
    }

    @Test
    public void whenGetMercancyById_thenThrowExceptionIfNotFound() {
        when(mercancyRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> mercancyService.getMercancyById(99L));
    }

    // **Test: Actualizar una Mercancía**
    @Test
    public void whenUpdateMercancy_thenReturnUpdatedMercancyEntity() {
        MercancyEntity existingMercancy = new MercancyEntity();
        existingMercancy.setId(1L);
        existingMercancy.setName("Old Name");

        MercancyEntity updatedMercancy = new MercancyEntity();
        updatedMercancy.setName("Updated Name");

        when(mercancyRepository.findById(1L)).thenReturn(Optional.of(existingMercancy));
        when(mercancyRepository.save(any(MercancyEntity.class))).thenReturn(updatedMercancy);

        MercancyEntity result = mercancyService.updateMercancy(1L, updatedMercancy);

        assertEquals("Updated Name", result.getName());
    }

    @Test
    public void whenUpdateMercancy_thenThrowExceptionIfNotFound() {
        when(mercancyRepository.findById(99L)).thenReturn(Optional.empty());

        MercancyEntity updatedMercancy = new MercancyEntity();
        updatedMercancy.setName("Updated Name");

        assertThrows(RuntimeException.class, () -> mercancyService.updateMercancy(99L, updatedMercancy));
    }

    // **Test: Eliminar una Mercancía por ID**
    @Test
    public void whenDeleteMercancy_thenDoNothingIfExists() {
        when(mercancyRepository.existsById(1L)).thenReturn(true);

        mercancyService.deleteMercancy(1L);

        verify(mercancyRepository, times(1)).deleteById(1L);
    }

    @Test
    public void whenDeleteMercancy_thenThrowExceptionIfNotFound() {
        when(mercancyRepository.existsById(99L)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> mercancyService.deleteMercancy(99L));
    }

    // **Test: Obtener Mercancías por Character ID**
    @Test
    public void whenGetMercancyByCharacterId_thenReturnListOfMercancy() {
        Long characterId = 1L;
        MercancyEntity mercancy1 = new MercancyEntity();
        mercancy1.setCharacterId(characterId);
        MercancyEntity mercancy2 = new MercancyEntity();
        mercancy2.setCharacterId(characterId);

        when(mercancyRepository.findByCharacterId(characterId)).thenReturn(Arrays.asList(mercancy1, mercancy2));

        List<MercancyEntity> mercancies = mercancyService.getMercancyByCharacterId(characterId);

        assertThat(mercancies).hasSize(2);
    }

    // **Test: Obtener Mercancías por nombre**
    @Test
    public void whenGetMercancyByName_thenReturnListOfMercancy() {
        String name = "Test";
        MercancyEntity mercancy = new MercancyEntity();
        mercancy.setName("Test Mercancy");

        when(mercancyRepository.findByNameContainingIgnoreCase(name)).thenReturn(List.of(mercancy));

        List<MercancyEntity> mercancies = mercancyService.getMercancyByName(name);

        assertThat(mercancies).hasSize(1);
        assertEquals("Test Mercancy", mercancies.get(0).getName());
    }

    // **Test: Obtener Mercancías con precio superior**
    @Test
    public void whenGetMercancyByPriceGreaterThan_thenReturnListOfMercancy() {
        int price = 100;
        MercancyEntity mercancy = new MercancyEntity();
        mercancy.setPrice(200);

        when(mercancyRepository.findByPriceGreaterThan(price)).thenReturn(List.of(mercancy));

        List<MercancyEntity> mercancies = mercancyService.getMercancyByPriceGreaterThan(price);

        assertThat(mercancies).hasSize(1);
        assertEquals(200, mercancies.get(0).getPrice());
    }

    // **Test: Obtener Mercancías con precio inferior**
    @Test
    public void whenGetMercancyByPriceLessThan_thenReturnListOfMercancy() {
        int price = 200;
        MercancyEntity mercancy = new MercancyEntity();
        mercancy.setPrice(100);

        when(mercancyRepository.findByPriceLessThan(price)).thenReturn(List.of(mercancy));

        List<MercancyEntity> mercancies = mercancyService.getMercancyByPriceLessThan(price);

        assertThat(mercancies).hasSize(1);
        assertEquals(100, mercancies.get(0).getPrice());
    }
}

package com.example.demoAllTouhou.services;

import com.example.demoAllTouhou.entities.MerchEntity;
import com.example.demoAllTouhou.repositories.MerchRepository;
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
public class MerchServiceTest {

    @InjectMocks
    private MerchService merchService;

    @Mock
    private MerchRepository merchRepository;

    // **Test: Crear una nueva Mercancía**
    @Test
    public void whenCreateMerch_thenReturnmerchEntity() {
        MerchEntity merch = new MerchEntity();
        merch.setName("New merch");
        merch.setPrice(100);

        when(merchRepository.save(any(MerchEntity.class))).thenReturn(merch);

        MerchEntity createdmerch = merchService.createMerch(merch);

        assertEquals("New merch", createdmerch.getName());
        assertEquals(100, createdmerch.getPrice());
    }

    // **Test: Obtener todas las Mercancías**
    @Test
    public void whenGetAllMerch_thenReturnListOfmerch() {
        MerchEntity merch1 = new MerchEntity();
        merch1.setName("merch 1");
        MerchEntity merch2 = new MerchEntity();
        merch2.setName("merch 2");

        when(merchRepository.findAll()).thenReturn(Arrays.asList(merch1, merch2));

        List<MerchEntity> mercancies = merchService.getAllMerch();

        assertThat(mercancies).hasSize(2);
        assertThat(mercancies).extracting(MerchEntity::getName)
                .containsExactlyInAnyOrder("merch 1", "merch 2");
    }

    // **Test: Obtener Mercancía por ID**
    @Test
    public void whenGetMerchById_thenReturnmerchEntity() {
        MerchEntity merch = new MerchEntity();
        merch.setId(1L);
        merch.setName("merch Name");

        when(merchRepository.findById(1L)).thenReturn(Optional.of(merch));

        MerchEntity result = merchService.getMerchById(1L);

        assertEquals(1L, result.getId());
        assertEquals("merch Name", result.getName());
    }

    @Test
    public void whenGetMerchById_thenThrowExceptionIfNotFound() {
        when(merchRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> merchService.getMerchById(99L));
    }

    // **Test: Actualizar una Mercancía**
    @Test
    public void whenUpdateMerch_thenReturnUpdatedmerchEntity() {
        MerchEntity existingmerch = new MerchEntity();
        existingmerch.setId(1L);
        existingmerch.setName("Old Name");

        MerchEntity updatedmerch = new MerchEntity();
        updatedmerch.setName("Updated Name");

        when(merchRepository.findById(1L)).thenReturn(Optional.of(existingmerch));
        when(merchRepository.save(any(MerchEntity.class))).thenReturn(updatedmerch);

        MerchEntity result = merchService.updateMerch(1L, updatedmerch);

        assertEquals("Updated Name", result.getName());
    }

    @Test
    public void whenUpdateMerch_thenThrowExceptionIfNotFound() {
        when(merchRepository.findById(99L)).thenReturn(Optional.empty());

        MerchEntity updatedmerch = new MerchEntity();
        updatedmerch.setName("Updated Name");

        assertThrows(RuntimeException.class, () -> merchService.updateMerch(99L, updatedmerch));
    }

    // **Test: Eliminar una Mercancía por ID**
    @Test
    public void whenDeleteMerch_thenDoNothingIfExists() {
        when(merchRepository.existsById(1L)).thenReturn(true);

        merchService.deleteMerch(1L);

        verify(merchRepository, times(1)).deleteById(1L);
    }

    @Test
    public void whenDeleteMerch_thenThrowExceptionIfNotFound() {
        when(merchRepository.existsById(99L)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> merchService.deleteMerch(99L));
    }

    // **Test: Obtener Mercancías por nombre**
    @Test
    public void whenGetMerchByName_thenReturnListOfmerch() {
        String name = "Test";
        MerchEntity merch = new MerchEntity();
        merch.setName("Test merch");

        when(merchRepository.findByNameContainingIgnoreCase(name)).thenReturn(List.of(merch));

        List<MerchEntity> mercancies = merchService.getMerchByName(name);

        assertThat(mercancies).hasSize(1);
        assertEquals("Test merch", mercancies.get(0).getName());
    }

    // **Test: Obtener Mercancías con precio superior**
    @Test
    public void whenGetMerchByPriceGreaterThan_thenReturnListOfmerch() {
        int price = 100;
        MerchEntity merch = new MerchEntity();
        merch.setPrice(200);

        when(merchRepository.findByPriceGreaterThan(price)).thenReturn(List.of(merch));

        List<MerchEntity> mercancies = merchService.getMerchByPriceGreaterThan(price);

        assertThat(mercancies).hasSize(1);
        assertEquals(200, mercancies.get(0).getPrice());
    }

    // **Test: Obtener Mercancías con precio inferior**
    @Test
    public void whenGetMerchByPriceLessThan_thenReturnListOfmerch() {
        int price = 200;
        MerchEntity merch = new MerchEntity();
        merch.setPrice(100);

        when(merchRepository.findByPriceLessThan(price)).thenReturn(List.of(merch));

        List<MerchEntity> mercancies = merchService.getMerchByPriceLessThan(price);

        assertThat(mercancies).hasSize(1);
        assertEquals(100, mercancies.get(0).getPrice());
    }
}

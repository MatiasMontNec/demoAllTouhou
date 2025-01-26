package com.example.demoAllTouhou.services;

import com.example.demoAllTouhou.entities.PowersEntity;
import com.example.demoAllTouhou.repositories.PowersRepository;
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
public class PowersServiceTest {

    @InjectMocks
    private PowersService powersService;

    @Mock
    private PowersRepository powersRepository;

    // **Test: Crear un nuevo Power**
    @Test
    public void whenCreatePower_thenReturnPowersEntity() {
        PowersEntity power = new PowersEntity();
        power.setName("Power Name");
        power.setDescription("Power Description");

        when(powersRepository.save(any(PowersEntity.class))).thenReturn(power);

        PowersEntity createdPower = powersService.createPower(power);

        assertEquals("Power Name", createdPower.getName());
        assertEquals("Power Description", createdPower.getDescription());
    }

    // **Test: Obtener todos los Powers**
    @Test
    public void whenGetAllPowers_thenReturnListOfPowers() {
        PowersEntity power1 = new PowersEntity();
        power1.setName("Power 1");

        PowersEntity power2 = new PowersEntity();
        power2.setName("Power 2");

        when(powersRepository.findAll()).thenReturn(Arrays.asList(power1, power2));

        List<PowersEntity> powers = powersService.getAllPowers();

        assertThat(powers).hasSize(2);
        assertThat(powers).extracting(PowersEntity::getName)
                .containsExactlyInAnyOrder("Power 1", "Power 2");
    }

    // **Test: Obtener un Power por ID**
    @Test
    public void whenGetPowerById_thenReturnPowersEntity() {
        PowersEntity power = new PowersEntity();
        power.setId(1L);
        power.setName("Power Name");

        when(powersRepository.findById(1L)).thenReturn(Optional.of(power));

        PowersEntity result = powersService.getPowerById(1L);

        assertEquals(1L, result.getId());
        assertEquals("Power Name", result.getName());
    }

    @Test
    public void whenGetPowerById_thenThrowExceptionIfNotFound() {
        when(powersRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> powersService.getPowerById(99L));
    }

    // **Test: Actualizar un Power**
    @Test
    public void whenUpdatePower_thenReturnUpdatedPowersEntity() {
        PowersEntity existingPower = new PowersEntity();
        existingPower.setId(1L);
        existingPower.setName("Old Name");
        existingPower.setDescription("Old Description");

        PowersEntity updatedPower = new PowersEntity();
        updatedPower.setName("New Name");
        updatedPower.setDescription("New Description");

        when(powersRepository.findById(1L)).thenReturn(Optional.of(existingPower));
        when(powersRepository.save(any(PowersEntity.class))).thenReturn(updatedPower);

        PowersEntity result = powersService.updatePower(1L, updatedPower);

        assertEquals("New Name", result.getName());
        assertEquals("New Description", result.getDescription());
    }

    @Test
    public void whenUpdatePower_thenThrowExceptionIfNotFound() {
        when(powersRepository.findById(99L)).thenReturn(Optional.empty());

        PowersEntity updatedPower = new PowersEntity();
        updatedPower.setName("New Name");
        updatedPower.setDescription("New Description");

        assertThrows(RuntimeException.class, () -> powersService.updatePower(99L, updatedPower));
    }

    // **Test: Eliminar un Power por ID**
    @Test
    public void whenDeletePower_thenDoNothingIfExists() {
        when(powersRepository.existsById(1L)).thenReturn(true);

        powersService.deletePower(1L);

        verify(powersRepository, times(1)).deleteById(1L);
    }

    @Test
    public void whenDeletePower_thenThrowExceptionIfNotFound() {
        when(powersRepository.existsById(99L)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> powersService.deletePower(99L));
    }

    // **Test: Obtener Powers por Name (contiene string)**
    @Test
    public void whenGetPowersByName_thenReturnListOfPowers() {
        String name = "Power";

        PowersEntity power1 = new PowersEntity();
        power1.setName("Power 1");

        PowersEntity power2 = new PowersEntity();
        power2.setName("Power 2");

        when(powersRepository.findByNameContainingIgnoreCase(name)).thenReturn(Arrays.asList(power1, power2));

        List<PowersEntity> powers = powersService.getPowersByName(name);

        assertThat(powers).hasSize(2);
        assertThat(powers).extracting(PowersEntity::getName)
                .containsExactlyInAnyOrder("Power 1", "Power 2");
    }
}

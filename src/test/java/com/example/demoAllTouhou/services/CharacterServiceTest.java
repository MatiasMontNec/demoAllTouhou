package com.example.demoAllTouhou.services;

import com.example.demoAllTouhou.entities.CharacterEntity;
import com.example.demoAllTouhou.repositories.CharacterRepository;
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
public class CharacterServiceTest {

    @InjectMocks
    private CharacterService characterService;  // Inyecta los mocks en el servicio

    @Mock
    private CharacterRepository characterRepository;  // Mock del repositorio

    // **Test: Crear un nuevo personaje**
    @Test
    public void whenCreateCharacter_thenReturnCharacterEntity() {
        CharacterEntity character = new CharacterEntity();
        character.setName("Reimu Hakurei");
        character.setAge(17);

        when(characterRepository.save(any(CharacterEntity.class))).thenReturn(character);

        CharacterEntity createdCharacter = characterService.createCharacter(character);

        assertEquals("Reimu Hakurei", createdCharacter.getName());
        assertEquals(17, createdCharacter.getAge());
    }

    // **Test: Obtener todos los personajes**
    @Test
    public void whenGetAllCharacters_thenReturnListOfCharacters() {
        CharacterEntity character1 = new CharacterEntity();
        character1.setName("Marisa Kirisame");
        CharacterEntity character2 = new CharacterEntity();
        character2.setName("Alice Margatroid");

        when(characterRepository.findAll()).thenReturn(Arrays.asList(character1, character2));

        List<CharacterEntity> characters = characterService.getAllCharacters();

        assertThat(characters).hasSize(2);
        assertThat(characters).extracting(CharacterEntity::getName)
                .containsExactlyInAnyOrder("Marisa Kirisame", "Alice Margatroid");
    }

    // **Test: Obtener un personaje por ID**
    @Test
    public void whenGetCharacterById_thenReturnCharacterEntity() {
        CharacterEntity character = new CharacterEntity();
        character.setId(1L);
        character.setName("Youmu Konpaku");

        when(characterRepository.findById(1L)).thenReturn(Optional.of(character));

        CharacterEntity result = characterService.getCharacterById(1L);

        assertEquals("Youmu Konpaku", result.getName());
    }

    @Test
    public void whenGetCharacterById_thenThrowExceptionIfNotFound() {
        when(characterRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> characterService.getCharacterById(99L));
    }

    // **Test: Actualizar un personaje existente**
    @Test
    public void whenUpdateCharacter_thenReturnUpdatedCharacter() {
        CharacterEntity existingCharacter = new CharacterEntity();
        existingCharacter.setId(1L);
        existingCharacter.setName("Aya Shameimaru");

        CharacterEntity updatedCharacter = new CharacterEntity();
        updatedCharacter.setName("Updated Aya");

        when(characterRepository.findById(1L)).thenReturn(Optional.of(existingCharacter));
        when(characterRepository.save(any(CharacterEntity.class))).thenReturn(updatedCharacter);

        CharacterEntity result = characterService.updateCharacter(1L, updatedCharacter);

        assertEquals("Updated Aya", result.getName());
    }

    @Test
    public void whenUpdateCharacter_thenThrowExceptionIfNotFound() {
        when(characterRepository.findById(99L)).thenReturn(Optional.empty());

        CharacterEntity updatedCharacter = new CharacterEntity();
        updatedCharacter.setName("Doesn't matter");

        assertThrows(RuntimeException.class, () -> characterService.updateCharacter(99L, updatedCharacter));
    }

    // **Test: Eliminar un personaje por ID**
    @Test
    public void whenDeleteCharacter_thenDoNothingIfExists() {
        when(characterRepository.existsById(1L)).thenReturn(true);

        characterService.deleteCharacter(1L);
    }

    @Test
    public void whenDeleteCharacter_thenThrowExceptionIfNotFound() {
        when(characterRepository.existsById(99L)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> characterService.deleteCharacter(99L));
    }

    // **Test: Buscar un personaje por nombre**
    @Test
    public void whenGetCharacterByName_thenReturnCharacterEntity() {
        CharacterEntity character = new CharacterEntity();
        character.setName("Cirno");

        when(characterRepository.findByName("Cirno")).thenReturn(Optional.of(character));

        CharacterEntity result = characterService.getCharacterByName("Cirno");

        assertEquals("Cirno", result.getName());
    }

    // **Test: Encontrar personajes con edad menor o igual a la ingresada**
    @Test
    public void whenGetCharactersByAgeLessThanOrEqual_thenReturnList() {
        CharacterEntity character1 = new CharacterEntity();
        character1.setAge(16);
        CharacterEntity character2 = new CharacterEntity();
        character2.setAge(17);

        when(characterRepository.findByAgeLessThanEqual(17)).thenReturn(Arrays.asList(character1, character2));

        List<CharacterEntity> characters = characterService.getCharactersByAgeLessThanOrEqual(17);

        assertThat(characters).hasSize(2);
    }

    // **Test: Encontrar personajes con altura mayor a la ingresada**
    @Test
    public void whenGetCharactersByHeightGreaterThan_thenReturnList() {
        CharacterEntity character = new CharacterEntity();
        character.setHeight(160);

        when(characterRepository.findByHeightGreaterThan(150)).thenReturn(List.of(character));

        List<CharacterEntity> characters = characterService.getCharactersByHeightGreaterThan(150);

        assertThat(characters).hasSize(1);
        assertThat(characters.get(0).getHeight()).isEqualTo(160);
    }

    // **Test: Encontrar personajes con edad mayor o igual a la ingresada**
    @Test
    public void whenGetCharactersByAgeGreaterThanOrEqual_thenReturnList() {
        CharacterEntity character1 = new CharacterEntity();
        character1.setAge(18);
        CharacterEntity character2 = new CharacterEntity();
        character2.setAge(20);

        when(characterRepository.findByAgeGreaterThanEqual(18)).thenReturn(Arrays.asList(character1, character2));

        List<CharacterEntity> characters = characterService.getCharactersByAgeGreaterThanOrEqual(18);

        assertThat(characters).hasSize(2);
        assertThat(characters.get(0).getAge()).isGreaterThanOrEqualTo(18);
        assertThat(characters.get(1).getAge()).isGreaterThanOrEqualTo(18);
    }

    // **Test: Encontrar personajes con altura menor a la ingresada**
    @Test
    public void whenGetCharactersByHeightLessThan_thenReturnList() {
        CharacterEntity character1 = new CharacterEntity();
        character1.setHeight(140);
        CharacterEntity character2 = new CharacterEntity();
        character2.setHeight(145);

        when(characterRepository.findByHeightLessThan(150)).thenReturn(Arrays.asList(character1, character2));

        List<CharacterEntity> characters = characterService.getCharactersByHeightLessThan(150);

        assertThat(characters).hasSize(2);
        assertThat(characters.get(0).getHeight()).isLessThan(150);
        assertThat(characters.get(1).getHeight()).isLessThan(150);
    }

    // **Test: Encontrar personajes con peso mayor al ingresado**
    @Test
    public void whenGetCharactersByWeightGreaterThan_thenReturnList() {
        CharacterEntity character1 = new CharacterEntity();
        character1.setWeight(60);
        CharacterEntity character2 = new CharacterEntity();
        character2.setWeight(70);

        when(characterRepository.findByWeightGreaterThan(50)).thenReturn(Arrays.asList(character1, character2));

        List<CharacterEntity> characters = characterService.getCharactersByWeightGreaterThan(50);

        assertThat(characters).hasSize(2);
        assertThat(characters.get(0).getWeight()).isGreaterThan(50);
        assertThat(characters.get(1).getWeight()).isGreaterThan(50);
    }

    // **Test: Encontrar personajes con peso menor al ingresado**
    @Test
    public void whenGetCharactersByWeightLessThan_thenReturnList() {
        CharacterEntity character1 = new CharacterEntity();
        character1.setWeight(40);
        CharacterEntity character2 = new CharacterEntity();
        character2.setWeight(45);

        when(characterRepository.findByWeightLessThan(50)).thenReturn(Arrays.asList(character1, character2));

        List<CharacterEntity> characters = characterService.getCharactersByWeightLessThan(50);

        assertThat(characters).hasSize(2);
        assertThat(characters.get(0).getWeight()).isLessThan(50);
        assertThat(characters.get(1).getWeight()).isLessThan(50);
    }

    // **Test: Encontrar personajes que contienen un texto en importantFacts**
    @Test
    public void whenGetCharactersByImportantFactsContaining_thenReturnList() {
        CharacterEntity character1 = new CharacterEntity();
        character1.setImportantFacts("Reimu is a shrine maiden.");
        CharacterEntity character2 = new CharacterEntity();
        character2.setImportantFacts("Marisa loves magic.");

        // Solo esperamos que character2 contenga la palabra "magic"
        when(characterRepository.findByImportantFactsContainingIgnoreCase("magic")).thenReturn(Arrays.asList(character2));

        List<CharacterEntity> characters = characterService.getCharactersByImportantFactsContaining("magic");

        assertThat(characters).hasSize(1);  // Solo debería haber un personaje que cumpla con la condición
        assertThat(characters.get(0).getImportantFacts()).containsIgnoringCase("magic");
    }

    // **Test: Buscar personajes por especie**
    @Test
    public void whenGetCharactersBySpecies_thenReturnList() {
        CharacterEntity character1 = new CharacterEntity();
        character1.setSpecies("Human");
        CharacterEntity character2 = new CharacterEntity();
        character2.setSpecies("Youkai");

        when(characterRepository.findBySpeciesIgnoreCase("human")).thenReturn(Arrays.asList(character1));

        List<CharacterEntity> characters = characterService.getCharactersBySpecies("human");

        assertThat(characters).hasSize(1);
        assertThat(characters.get(0).getSpecies()).isEqualToIgnoringCase("Human");
    }

}

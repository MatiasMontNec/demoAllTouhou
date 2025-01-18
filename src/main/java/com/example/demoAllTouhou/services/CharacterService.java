package com.example.demoAllTouhou.services;

import com.example.demoAllTouhou.entities.CharacterEntity;
import com.example.demoAllTouhou.repositories.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterService {
    @Autowired
    CharacterRepository characterRepository;

    // **Crear un nuevo personaje**
    public CharacterEntity createCharacter(CharacterEntity characterEntity) {
        return characterRepository.save(characterEntity);
    }

    // **Obtener todos los personajes**
    public List<CharacterEntity> getAllCharacters() {
        return characterRepository.findAll();
    }

    // **Obtener un personaje por ID**
    public CharacterEntity getCharacterById(Long id) {
        return characterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Character not found with id: " + id));
    }

    // **Actualizar un personaje existente**
    public CharacterEntity updateCharacter(Long id, CharacterEntity updatedCharacter) {
        CharacterEntity existingCharacter = characterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Character not found with id: " + id));

        // Actualizamos los campos
        existingCharacter.setName(updatedCharacter.getName());
        existingCharacter.setAge(updatedCharacter.getAge());
        existingCharacter.setGender(updatedCharacter.getGender());
        existingCharacter.setHeight(updatedCharacter.getHeight());
        existingCharacter.setWeight(updatedCharacter.getWeight());
        existingCharacter.setBiography(updatedCharacter.getBiography());
        existingCharacter.setRelations(updatedCharacter.getRelations());
        existingCharacter.setImportantFacts(updatedCharacter.getImportantFacts());

        return characterRepository.save(existingCharacter);
    }

    // **Eliminar un personaje por ID**
    public void deleteCharacter(Long id) {
        if (!characterRepository.existsById(id)) {
            throw new RuntimeException("Character not found with id: " + id);
        }
        characterRepository.deleteById(id);
    }

    // **Buscar un personaje por nombre**
    public CharacterEntity getCharacterByName(String name) {
        return characterRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Character not found with name: " + name));
    }

    // **Encontrar personajes con edad menor o igual a la ingresada**
    public List<CharacterEntity> getCharactersByAgeLessThanOrEqual(int age) {
        return characterRepository.findByAgeLessThanOrEqual(age);
    }

    // **Encontrar personajes con edad mayor o igual a la ingresada**
    public List<CharacterEntity> getCharactersByAgeGreaterThanOrEqual(int age) {
        return characterRepository.findByAgeGreaterThanOrEqual(age);
    }

    // **Encontrar personajes con altura mayor a la ingresada**
    public List<CharacterEntity> getCharactersByHeightGreaterThan(int height) {
        return characterRepository.findByHeightGreaterThan(height);
    }

    // **Encontrar personajes con altura menor a la ingresada**
    public List<CharacterEntity> getCharactersByHeightLessThan(int height) {
        return characterRepository.findByHeightLessThan(height);
    }

    // **Encontrar personajes con peso mayor al ingresado**
    public List<CharacterEntity> getCharactersByWeightGreaterThan(int weight) {
        return characterRepository.findByWeightGreaterThan(weight);
    }

    // **Encontrar personajes con peso menor al ingresado**
    public List<CharacterEntity> getCharactersByWeightLessThan(int weight) {
        return characterRepository.findByWeightLessThan(weight);
    }

    // **Encontrar personajes que contienen un texto en importantFacts**
    public List<CharacterEntity> getCharactersByImportantFactsContaining(String keyword) {
        return characterRepository.findByImportantFactsContainingIgnoreCase(keyword);
    }

    // **Buscar personajes por especie**
    public List<CharacterEntity> getCharactersBySpecies(String species) {
        return characterRepository.findBySpeciesIgnoreCase(species);
    }
}
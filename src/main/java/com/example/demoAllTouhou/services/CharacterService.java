package com.example.demoAllTouhou.services;

import com.example.demoAllTouhou.entities.CharacterEntity;
import com.example.demoAllTouhou.repositories.CharacterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterService {
    final CharacterRepository characterRepository;

    public CharacterService(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

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

    // **Actualizar un personaje existente usando id y el cuerpo**
    public CharacterEntity updateCharacter(Long id, CharacterEntity updatedCharacter) {
        if ( updatedCharacter == null ) throw new RuntimeException("Character is null, can't update");

        CharacterEntity existingCharacter = characterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Character not found with id: " + id));

        // Actualizamos los campos
        if (updatedCharacter.getName() != null && !updatedCharacter.getName().isEmpty()) existingCharacter.setName(updatedCharacter.getName());
        if (updatedCharacter.getAge() != 0) existingCharacter.setAge(updatedCharacter.getAge());
        if (updatedCharacter.getGender() != null && !updatedCharacter.getGender().isEmpty()) existingCharacter.setGender(updatedCharacter.getGender());
        if (updatedCharacter.getHeight() != 0) existingCharacter.setHeight(updatedCharacter.getHeight());
        if (updatedCharacter.getHeight() != 0) existingCharacter.setWeight(updatedCharacter.getWeight());
        if (updatedCharacter.getBiography() != null && !updatedCharacter.getBiography().isEmpty()) existingCharacter.setBiography(updatedCharacter.getBiography());
        if (updatedCharacter.getRelations() != null && !updatedCharacter.getRelations().isEmpty()) existingCharacter.setRelations(updatedCharacter.getRelations());
        if (updatedCharacter.getImportantFacts() != null && !updatedCharacter.getImportantFacts().isEmpty()) existingCharacter.setImportantFacts(updatedCharacter.getImportantFacts());
        if (updatedCharacter.getLivesIn() != null && !updatedCharacter.getLivesIn().isEmpty()) existingCharacter.setLivesIn(updatedCharacter.getLivesIn());
        if (updatedCharacter.getDislikes() != null && !updatedCharacter.getDislikes().isEmpty()) existingCharacter.setDislikes(updatedCharacter.getDislikes());
        if (updatedCharacter.getLikes() != null && !updatedCharacter.getLikes().isEmpty()) existingCharacter.setLikes(updatedCharacter.getLikes());
        if (updatedCharacter.getPowers() != null && !updatedCharacter.getPowers().isEmpty()) existingCharacter.setPowers(updatedCharacter.getPowers());
        if (updatedCharacter.getSpecies() != null && !updatedCharacter.getSpecies().isEmpty()) existingCharacter.setSpecies(updatedCharacter.getSpecies());
        if (updatedCharacter.getGames() != null) existingCharacter.setGames(updatedCharacter.getGames());
        // ¿Por qué no con manga, canciones o mercancia? Porque puede que no pertenezcan a uno o que tade demasiado en cargar jeje
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
        return characterRepository.findByAgeLessThanEqual(age);
    }

    // **Encontrar personajes con edad mayor o igual a la ingresada**
    public List<CharacterEntity> getCharactersByAgeGreaterThanOrEqual(int age) {
        return characterRepository.findByAgeGreaterThanEqual(age);
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
        return characterRepository.findBySpecies_NameIgnoreCase(species);
    }
}
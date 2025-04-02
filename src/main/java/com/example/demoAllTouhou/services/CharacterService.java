package com.example.demoAllTouhou.services;

import com.example.demoAllTouhou.entities.CharacterEntity;
import com.example.demoAllTouhou.repositories.CharacterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        if (updatedCharacter == null) {
            throw new RuntimeException("Character is null, can't update");
        }

        // Buscar el personaje existente
        CharacterEntity existingCharacter = characterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Character not found with id: " + id));

        // Actualizar campos básicos
        if (updatedCharacter.getName() != null && !updatedCharacter.getName().isEmpty()) {
            existingCharacter.setName(updatedCharacter.getName());
        }
        if (updatedCharacter.getAge() != 0) {
            existingCharacter.setAge(updatedCharacter.getAge());
        }
        if (updatedCharacter.getGender() != null && !updatedCharacter.getGender().isEmpty()) {
            existingCharacter.setGender(updatedCharacter.getGender());
        }
        if (updatedCharacter.getHeight() != 0) {
            existingCharacter.setHeight(updatedCharacter.getHeight());
        }
        if (updatedCharacter.getWeight() != 0) {
            existingCharacter.setWeight(updatedCharacter.getWeight());
        }
        if (updatedCharacter.getBiography() != null && !updatedCharacter.getBiography().isEmpty()) {
            existingCharacter.setBiography(updatedCharacter.getBiography());
        }
        if (updatedCharacter.getLivesIn() != null && !updatedCharacter.getLivesIn().isEmpty()) {
            existingCharacter.setLivesIn(updatedCharacter.getLivesIn());
        }

        // Actualizar el grupo de especie
        if (updatedCharacter.getGroupSpecies() != 0) {
            existingCharacter.setGroupSpecies(updatedCharacter.getGroupSpecies());
        }

        // Guardar y devolver el personaje actualizado
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
    /**
    public List<CharacterEntity> getCharactersByImportantFactsContaining(String keyword) {
        return characterRepository.findByImportantFactsContainingIgnoreCase(keyword);
    }

    // **Buscar personajes por especie**
    public List<CharacterEntity> getCharactersBySpecies(String species) {
        return characterRepository.findBySpecies_NameIgnoreCase(species);
    }
    **/




    // Método auxiliar para mapear el ID del grupo de especie a un nombre
    private String getSpeciesName(int groupSpeciesId) {
        switch (groupSpeciesId) {
            case 0:
                return "Youkai";
            case 1:
                return "Espíritus";
            case 2:
                return "Seres divinos y celestiales";
            case 3:
                return "Seres humanos y semi-humanos";
            case 4:
                return "Criaturas mitológicas y folclóricas";
            case 5:
                return "Criaturas animales y tsukumogami";
            case 6:
                return "Especies exclusivas de Touhou";
            case 7:
                return "Otras clasificaciones";
            default:
                return "Desconocido";
        }
    }

    // Método para filtrar la lista de CharacterDTO según los parámetros opcionales
    public List<CharacterEntity> filterCharacters(
            List<CharacterEntity> characters,
            Integer edadMin,
            Integer edadMax,
            String genero,
            String especie,
            Integer alturaMin,
            Integer alturaMax,
            Integer pesoMin,
            Integer pesoMax
    ) {
        return characters.stream()
                .filter(character -> edadMin == null || character.getAge() >= edadMin)
                .filter(character -> edadMax == null || character.getAge() <= edadMax)
                .filter(character -> genero == null || character.getGender().equalsIgnoreCase(genero))
                .filter(character -> alturaMin == null || character.getHeight() >= alturaMin)
                .filter(character -> alturaMax == null || character.getHeight() <= alturaMax)
                .filter(character -> pesoMin == null || character.getWeight() >= pesoMin)
                .filter(character -> pesoMax == null || character.getWeight() <= pesoMax)
                .collect(Collectors.toList());
    }
}
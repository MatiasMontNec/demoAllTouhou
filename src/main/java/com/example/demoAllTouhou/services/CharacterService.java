package com.example.demoAllTouhou.services;

import com.example.demoAllTouhou.dto.CharacterDTO;
import com.example.demoAllTouhou.entities.CharacterEntity;
import com.example.demoAllTouhou.repositories.CharacterRepository;
import org.springframework.stereotype.Service;

import java.util.Base64;
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
        if (updatedCharacter.getRelations() != null && !updatedCharacter.getRelations().isEmpty()) {
            existingCharacter.setRelations(updatedCharacter.getRelations());
        }
        if (updatedCharacter.getImportantFacts() != null && !updatedCharacter.getImportantFacts().isEmpty()) {
            existingCharacter.setImportantFacts(updatedCharacter.getImportantFacts());
        }
        if (updatedCharacter.getLivesIn() != null && !updatedCharacter.getLivesIn().isEmpty()) {
            existingCharacter.setLivesIn(updatedCharacter.getLivesIn());
        }

        // Actualizar el grupo de especie
        if (updatedCharacter.getGroupSpecies() != 0) {
            existingCharacter.setGroupSpecies(updatedCharacter.getGroupSpecies());
        }

        // Actualizar listas de relaciones (dislikes, likes, powers, species)
        if (updatedCharacter.getDislikes() != null && !updatedCharacter.getDislikes().isEmpty()) {
            existingCharacter.setDislikes(updatedCharacter.getDislikes());
        }
        if (updatedCharacter.getLikes() != null && !updatedCharacter.getLikes().isEmpty()) {
            existingCharacter.setLikes(updatedCharacter.getLikes());
        }
        if (updatedCharacter.getPowers() != null && !updatedCharacter.getPowers().isEmpty()) {
            existingCharacter.setPowers(updatedCharacter.getPowers());
        }
        if (updatedCharacter.getSpecies() != null && !updatedCharacter.getSpecies().isEmpty()) {
            existingCharacter.setSpecies(updatedCharacter.getSpecies());
        }

        // Actualizar relaciones con otros personajes (relatedCharacters)
        if (updatedCharacter.getRelatedCharacters() != null && !updatedCharacter.getRelatedCharacters().isEmpty()) {
            // Limpiamos las relaciones existentes para evitar duplicados
            existingCharacter.getRelatedCharacters().clear();
            // Añadimos las nuevas relaciones
            existingCharacter.getRelatedCharacters().addAll(updatedCharacter.getRelatedCharacters());
        }

        // Actualizar relaciones con juegos, manga, canciones y mercancía (si es necesario)
        if (updatedCharacter.getGames() != null) {
            existingCharacter.setGames(updatedCharacter.getGames());
        }
        if (updatedCharacter.getManga() != null) {
            existingCharacter.setManga(updatedCharacter.getManga());
        }
        if (updatedCharacter.getSongs() != null) {
            existingCharacter.setSongs(updatedCharacter.getSongs());
        }
        if (updatedCharacter.getMerch() != null) {
            existingCharacter.setMerch(updatedCharacter.getMerch());
        }
        if (updatedCharacter.getImages() != null) {
            existingCharacter.setImages(updatedCharacter.getImages());
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
    public List<CharacterEntity> getCharactersByImportantFactsContaining(String keyword) {
        return characterRepository.findByImportantFactsContainingIgnoreCase(keyword);
    }

    // **Buscar personajes por especie**
    public List<CharacterEntity> getCharactersBySpecies(String species) {
        return characterRepository.findBySpecies_NameIgnoreCase(species);
    }

    // Obtener todos los personajes con los atributos específicos
    public List<CharacterDTO> getAllCharactersWithSelectedAttributes() {
        List<CharacterEntity> characters = characterRepository.findAll();

        return characters.stream()
                .map(this::mapToCharacterDTO)
                .collect(Collectors.toList());
    }

    // Transformar CharacterEntity en CharacterDTO
    private CharacterDTO mapToCharacterDTO(CharacterEntity character) {
        String firstImageUrl = character.getImages().stream()
                .filter(image -> "first".equalsIgnoreCase(image.getName()))
                .findFirst()
                .map(image -> "data:image/png;base64," + Base64.getEncoder().encodeToString(image.getImage()))
                .orElse(null);

        return new CharacterDTO(
                character.getId(),
                character.getName(),
                character.getAge(),
                character.getGender(),
                character.getHeight(),
                character.getWeight(),
                getSpeciesName(character.getGroupSpecies()),
                character.getBiography(),
                firstImageUrl
        );
    }

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
    public List<CharacterDTO> filterCharacters(
            List<CharacterDTO> characters,
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
                .filter(character -> edadMin == null || character.getEdad() >= edadMin)
                .filter(character -> edadMax == null || character.getEdad() <= edadMax)
                .filter(character -> genero == null || character.getGenero().equalsIgnoreCase(genero))
                .filter(character -> especie == null || character.getEspecie().equalsIgnoreCase(especie))
                .filter(character -> alturaMin == null || character.getAltura() >= alturaMin)
                .filter(character -> alturaMax == null || character.getAltura() <= alturaMax)
                .filter(character -> pesoMin == null || character.getPeso() >= pesoMin)
                .filter(character -> pesoMax == null || character.getPeso() <= pesoMax)
                .collect(Collectors.toList());
    }
}
package com.example.demoAllTouhou.services;

import com.example.demoAllTouhou.dto.GameDTO;
import com.example.demoAllTouhou.entities.GameEntity;
import com.example.demoAllTouhou.repositories.GameRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    // **Crear un nuevo Game**
    public GameEntity createGame(GameEntity gameEntity) {
        return gameRepository.save(gameEntity);
    }

    // **Obtener todos los Games**
    public List<GameEntity> getAllGames() {return gameRepository.findAll();}

    // **Obtener un Game por ID**
    public GameEntity getGameById(Long id) {
        return gameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Game not found with id: " + id));
    }

    // **Actualizar un Game**
    public GameEntity updateGame(Long id, GameEntity updatedGame) {
        if (updatedGame == null) throw new IllegalArgumentException("Updated game data must not be null.");

        GameEntity existingGame = gameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Game not found with id: " + id));

        // Actualizar el título si no es nulo ni vacío
        if (updatedGame.getTitle() != null && !updatedGame.getTitle().isEmpty()) {
            existingGame.setTitle(updatedGame.getTitle());
        }

        // Actualizar la descripción si no es nula ni vacía
        if (updatedGame.getDescription() != null && !updatedGame.getDescription().isEmpty()) {
            existingGame.setDescription(updatedGame.getDescription());
        }

        // Actualizar el enlace de descarga si no es nulo ni vacío
        if (updatedGame.getLinkDownload() != null && !updatedGame.getLinkDownload().isEmpty()) {
            existingGame.setLinkDownload(updatedGame.getLinkDownload());
        }

        // Actualizar la fecha de lanzamiento si no es nula y es diferente a la actual
        if (updatedGame.getReleaseDate() != null && !updatedGame.getReleaseDate().isEqual(existingGame.getReleaseDate())) {
            existingGame.setReleaseDate(updatedGame.getReleaseDate());
        }

        // Actualizar el género si es válido (opcionalmente puedes validar rangos si es un número categórico)
        if (updatedGame.getGenre() >= 0) { // Aquí asumimos que género no puede ser negativo
            existingGame.setGenre(updatedGame.getGenre());
        }

        // Guardar y devolver la entidad actualizada
        return gameRepository.save(existingGame);
    }

    // **Eliminar un Game por ID**
    public void deleteGame(Long id) {
        if (!gameRepository.existsById(id)) {
            throw new RuntimeException("Game not found with id: " + id);
        }
        gameRepository.deleteById(id);
    }

    // **Obtener un Game por título**
    public GameEntity getGameByTitle(String title) {
        return gameRepository.findByTitle(title)
                .orElseThrow(() -> new RuntimeException("Game not found with title: " + title));
    }

    // **Obtener todos los Games que salieron antes de una fecha**
    public List<GameEntity> getGamesBeforeDate(LocalDate date) {
        return gameRepository.findByReleaseDateBefore(date);
    }

    // **Obtener todos los Games que salieron después de una fecha**
    public List<GameEntity> getGamesAfterDate(LocalDate date) {
        return gameRepository.findByReleaseDateAfter(date);
    }

    // **Obtener todos los Games que salieron antes de una fecha con año, mes, día**
    public List<GameEntity> getGamesBeforeSpecificDate(int year, int month, int day) {
        LocalDate specificDate = LocalDate.of(year, month, day);
        return gameRepository.findByReleaseDateBefore(specificDate);
    }

    // **Obtener todos los Games que salieron después de una fecha con año, mes, día**
    public List<GameEntity> getGamesAfterSpecificDate(int year, int month, int day) {
        LocalDate specificDate = LocalDate.of(year, month, day);
        return gameRepository.findByReleaseDateAfter(specificDate);
    }

    // **Obtener todos los Games con el formato requerido**
    public List<GameDTO> getAllFormattedGames() {
        // Obtener todos los juegos
        List<GameEntity> games = gameRepository.findAll();

        // Transformar cada juego al formato requerido
        return games.stream().map(game -> {
            // Buscar la imagen con nombre "first"
            String firstImage = game.getImages().stream()
                    .filter(image -> "first".equalsIgnoreCase(image.getName()))
                    .findFirst()
                    .map(image -> "data:image/png;base64," + java.util.Base64.getEncoder().encodeToString(image.getImage()))
                    .orElse("https://via.placeholder.com/150");

            // Obtener el nombre del género basado en el ID
            String genreName = mapGenreIdToName(game.getGenre());

            // Mapear al DTO
            return new GameDTO(
                    game.getId(),
                    game.getTitle(),
                    game.getDescription(),
                    game.getReleaseDate().getYear(),
                    genreName,
                    firstImage
            );
        }).collect(Collectors.toList());
    }

    // Mapeo de ID de género al nombre
    private String mapGenreIdToName(int genreId) {
        switch (genreId) {
            case 1: return "Bullet Hell (Danmaku)";
            case 2: return "Juegos de Lucha";
            case 3: return "Juegos de Rol (RPG)";
            case 4: return "Juegos de Ritmo";
            case 5: return "Roguelike";
            case 6: return "Estrategia por Turnos";
            case 7: return "Visual Novels";
            case 8: return "Juegos de Puzles";
            case 9: return "Juegos de Mesa Digitales";
            case 10: return "Estrategia en Tiempo Real";
            case 11: return "Juegos de carreras";
            case 12: return "Otro";
            default: return "Desconocido";
        }
    }

    // Método para filtrar la lista de GameDTO según los parámetros opcionales
    public List<GameDTO> filterGames(
            List<GameDTO> games,
            Integer anioMin,
            Integer anioMax,
            String genero
    ) {
        return games.stream()
                .filter(game -> anioMin == null || game.getAño() >= anioMin)
                .filter(game -> anioMax == null || game.getAño() <= anioMax)
                .filter(game -> genero == null || game.getGenero().equalsIgnoreCase(genero))
                .collect(Collectors.toList());
    }
}

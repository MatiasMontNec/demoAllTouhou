package com.example.demoAllTouhou.services;

import com.example.demoAllTouhou.entities.GameEntity;
import com.example.demoAllTouhou.repositories.GameRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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
    public List<GameEntity> getAllGames() {
        return gameRepository.findAll();
    }

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
}

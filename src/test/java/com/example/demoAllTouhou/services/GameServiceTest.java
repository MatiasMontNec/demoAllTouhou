package com.example.demoAllTouhou.services;

import com.example.demoAllTouhou.entities.GameEntity;
import com.example.demoAllTouhou.repositories.GameRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GameServiceTest {

    @InjectMocks
    private GameService gameService;

    @Mock
    private GameRepository gameRepository;

    // **Test: Crear un nuevo Game**
    @Test
    public void whenCreateGame_thenReturnGameEntity() {
        GameEntity game = new GameEntity();
        game.setTitle("Game Title");
        game.setDescription("Game Description");
        game.setReleaseDate(LocalDate.of(2020, 1, 1));

        when(gameRepository.save(any(GameEntity.class))).thenReturn(game);

        GameEntity createdGame = gameService.createGame(game);

        assertEquals("Game Title", createdGame.getTitle());
        assertEquals("Game Description", createdGame.getDescription());
        assertEquals(LocalDate.of(2020, 1, 1), createdGame.getReleaseDate());
    }

    // **Test: Obtener todos los Games**
    @Test
    public void whenGetAllGames_thenReturnListOfGames() {
        GameEntity game1 = new GameEntity();
        game1.setTitle("Game 1");

        GameEntity game2 = new GameEntity();
        game2.setTitle("Game 2");

        when(gameRepository.findAll()).thenReturn(Arrays.asList(game1, game2));

        List<GameEntity> games = gameService.getAllGames();

        assertThat(games).hasSize(2);
        assertThat(games).extracting(GameEntity::getTitle)
                .containsExactlyInAnyOrder("Game 1", "Game 2");
    }

    // **Test: Obtener un Game por ID**
    @Test
    public void whenGetGameById_thenReturnGameEntity() {
        GameEntity game = new GameEntity();
        game.setId(1L);
        game.setTitle("Game Title");

        when(gameRepository.findById(1L)).thenReturn(Optional.of(game));

        GameEntity result = gameService.getGameById(1L);

        assertEquals(1L, result.getId());
        assertEquals("Game Title", result.getTitle());
    }

    @Test
    public void whenGetGameById_thenThrowExceptionIfNotFound() {
        when(gameRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> gameService.getGameById(99L));
    }

    // **Test: Actualizar un Game**
    @Test
    public void whenUpdateGame_thenReturnUpdatedGameEntity() {
        GameEntity existingGame = new GameEntity();
        existingGame.setId(1L);
        existingGame.setTitle("Old Title");
        existingGame.setDescription("Old Description");

        GameEntity updatedGame = new GameEntity();
        updatedGame.setTitle("New Title");
        updatedGame.setDescription("New Description");

        when(gameRepository.findById(1L)).thenReturn(Optional.of(existingGame));
        when(gameRepository.save(any(GameEntity.class))).thenReturn(updatedGame);

        GameEntity result = gameService.updateGame(1L, updatedGame);

        assertEquals("New Title", result.getTitle());
        assertEquals("New Description", result.getDescription());
    }

    @Test
    public void whenUpdateGame_thenThrowExceptionIfNotFound() {
        when(gameRepository.findById(99L)).thenReturn(Optional.empty());

        GameEntity updatedGame = new GameEntity();
        updatedGame.setTitle("New Title");

        assertThrows(RuntimeException.class, () -> gameService.updateGame(99L, updatedGame));
    }

    // **Test: Eliminar un Game**
    @Test
    public void whenDeleteGame_thenDoNothingIfExists() {
        when(gameRepository.existsById(1L)).thenReturn(true);

        gameService.deleteGame(1L);

        verify(gameRepository, times(1)).deleteById(1L);
    }

    @Test
    public void whenDeleteGame_thenThrowExceptionIfNotFound() {
        when(gameRepository.existsById(99L)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> gameService.deleteGame(99L));
    }

    // **Test: Obtener un Game por título**
    @Test
    public void whenGetGameByTitle_thenReturnGameEntity() {
        GameEntity game = new GameEntity();
        game.setTitle("Specific Title");

        when(gameRepository.findByTitle("Specific Title")).thenReturn(Optional.of(game));

        GameEntity result = gameService.getGameByTitle("Specific Title");

        assertEquals("Specific Title", result.getTitle());
    }

    @Test
    public void whenGetGameByTitle_thenThrowExceptionIfNotFound() {
        when(gameRepository.findByTitle("Unknown Title")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> gameService.getGameByTitle("Unknown Title"));
    }

    // **Test: Obtener todos los Games antes de una fecha**
    @Test
    public void whenGetGamesBeforeDate_thenReturnListOfGames() {
        LocalDate date = LocalDate.of(2020, 1, 1);

        GameEntity game1 = new GameEntity();
        game1.setReleaseDate(LocalDate.of(2019, 1, 1));

        GameEntity game2 = new GameEntity();
        game2.setReleaseDate(LocalDate.of(2018, 1, 1));

        when(gameRepository.findByReleaseDateBefore(date)).thenReturn(Arrays.asList(game1, game2));

        List<GameEntity> games = gameService.getGamesBeforeDate(date);

        assertThat(games).hasSize(2);
        assertThat(games).extracting(GameEntity::getReleaseDate)
                .containsExactlyInAnyOrder(LocalDate.of(2019, 1, 1), LocalDate.of(2018, 1, 1));
    }

    // **Test: Obtener todos los Games después de una fecha**
    @Test
    public void whenGetGamesAfterDate_thenReturnListOfGames() {
        LocalDate date = LocalDate.of(2020, 1, 1);

        GameEntity game1 = new GameEntity();
        game1.setReleaseDate(LocalDate.of(2021, 1, 1));

        when(gameRepository.findByReleaseDateAfter(date)).thenReturn(Arrays.asList(game1));

        List<GameEntity> games = gameService.getGamesAfterDate(date);

        assertThat(games).hasSize(1);
        assertThat(games).extracting(GameEntity::getReleaseDate)
                .containsExactly(LocalDate.of(2021, 1, 1));
    }

    // **Test: Obtener todos los Games que salieron antes de una fecha con año, mes, día**
    @Test
    public void whenGetGamesBeforeSpecificDate_thenReturnListOfGames() {
        int year = 2020;
        int month = 1;
        int day = 1;
        LocalDate specificDate = LocalDate.of(year, month, day);

        GameEntity game1 = new GameEntity();
        game1.setReleaseDate(LocalDate.of(2019, 1, 1));

        GameEntity game2 = new GameEntity();
        game2.setReleaseDate(LocalDate.of(2018, 1, 1));

        when(gameRepository.findByReleaseDateBefore(specificDate)).thenReturn(Arrays.asList(game1, game2));

        List<GameEntity> games = gameService.getGamesBeforeSpecificDate(year, month, day);

        assertThat(games).hasSize(2);
        assertThat(games).extracting(GameEntity::getReleaseDate)
                .containsExactlyInAnyOrder(LocalDate.of(2019, 1, 1), LocalDate.of(2018, 1, 1));
    }

    // **Test: Obtener todos los Games que salieron después de una fecha con año, mes, día**
    @Test
    public void whenGetGamesAfterSpecificDate_thenReturnListOfGames() {
        int year = 2020;
        int month = 1;
        int day = 1;
        LocalDate specificDate = LocalDate.of(year, month, day);

        GameEntity game1 = new GameEntity();
        game1.setReleaseDate(LocalDate.of(2021, 1, 1));

        GameEntity game2 = new GameEntity();
        game2.setReleaseDate(LocalDate.of(2022, 1, 1));

        when(gameRepository.findByReleaseDateAfter(specificDate)).thenReturn(Arrays.asList(game1, game2));

        List<GameEntity> games = gameService.getGamesAfterSpecificDate(year, month, day);

        assertThat(games).hasSize(2);
        assertThat(games).extracting(GameEntity::getReleaseDate)
                .containsExactlyInAnyOrder(LocalDate.of(2021, 1, 1), LocalDate.of(2022, 1, 1));
    }
}

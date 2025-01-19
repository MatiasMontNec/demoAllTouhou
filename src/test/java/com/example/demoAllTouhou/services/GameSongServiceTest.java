package com.example.demoAllTouhou.services;

import com.example.demoAllTouhou.entities.GameSongEntity;
import com.example.demoAllTouhou.repositories.GameSongRepository;
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
public class GameSongServiceTest {

    @InjectMocks
    private GameSongService gameSongService;

    @Mock
    private GameSongRepository gameSongRepository;

    // **Test: Crear un nuevo GameSong**
    @Test
    public void whenCreateGameSong_thenReturnGameSongEntity() {
        GameSongEntity gameSong = new GameSongEntity();
        gameSong.setSongId(1L);
        gameSong.setGameId(2L);

        when(gameSongRepository.save(any(GameSongEntity.class))).thenReturn(gameSong);

        GameSongEntity createdGameSong = gameSongService.createGameSong(gameSong);

        assertEquals(1L, createdGameSong.getSongId());
        assertEquals(2L, createdGameSong.getGameId());
    }

    // **Test: Obtener todos los GameSongs**
    @Test
    public void whenGetAllGameSongs_thenReturnListOfGameSongs() {
        GameSongEntity gameSong1 = new GameSongEntity();
        gameSong1.setSongId(1L);
        gameSong1.setGameId(2L);

        GameSongEntity gameSong2 = new GameSongEntity();
        gameSong2.setSongId(3L);
        gameSong2.setGameId(4L);

        when(gameSongRepository.findAll()).thenReturn(Arrays.asList(gameSong1, gameSong2));

        List<GameSongEntity> gameSongs = gameSongService.getAllGameSongs();

        assertThat(gameSongs).hasSize(2);
        assertThat(gameSongs).extracting(GameSongEntity::getSongId)
                .containsExactlyInAnyOrder(1L, 3L);
        assertThat(gameSongs).extracting(GameSongEntity::getGameId)
                .containsExactlyInAnyOrder(2L, 4L);
    }

    // **Test: Obtener un GameSong por ID**
    @Test
    public void whenGetGameSongById_thenReturnGameSongEntity() {
        GameSongEntity gameSong = new GameSongEntity();
        gameSong.setId(1L);
        gameSong.setSongId(2L);
        gameSong.setGameId(3L);

        when(gameSongRepository.findById(1L)).thenReturn(Optional.of(gameSong));

        GameSongEntity result = gameSongService.getGameSongById(1L);

        assertEquals(1L, result.getId());
        assertEquals(2L, result.getSongId());
        assertEquals(3L, result.getGameId());
    }

    @Test
    public void whenGetGameSongById_thenThrowExceptionIfNotFound() {
        when(gameSongRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> gameSongService.getGameSongById(99L));
    }

    // **Test: Actualizar un GameSong**
    @Test
    public void whenUpdateGameSong_thenReturnUpdatedGameSongEntity() {
        GameSongEntity existingGameSong = new GameSongEntity();
        existingGameSong.setId(1L);
        existingGameSong.setSongId(2L);
        existingGameSong.setGameId(3L);

        GameSongEntity updatedGameSong = new GameSongEntity();
        updatedGameSong.setSongId(4L);
        updatedGameSong.setGameId(5L);

        when(gameSongRepository.findById(1L)).thenReturn(Optional.of(existingGameSong));
        when(gameSongRepository.save(any(GameSongEntity.class))).thenReturn(updatedGameSong);

        GameSongEntity result = gameSongService.updateGameSong(1L, updatedGameSong);

        assertEquals(4L, result.getSongId());
        assertEquals(5L, result.getGameId());
    }

    @Test
    public void whenUpdateGameSong_thenThrowExceptionIfNotFound() {
        when(gameSongRepository.findById(99L)).thenReturn(Optional.empty());

        GameSongEntity updatedGameSong = new GameSongEntity();
        updatedGameSong.setSongId(4L);
        updatedGameSong.setGameId(5L);

        assertThrows(RuntimeException.class, () -> gameSongService.updateGameSong(99L, updatedGameSong));
    }

    // **Test: Eliminar un GameSong por ID**
    @Test
    public void whenDeleteGameSong_thenDoNothingIfExists() {
        when(gameSongRepository.existsById(1L)).thenReturn(true);

        gameSongService.deleteGameSong(1L);

        verify(gameSongRepository, times(1)).deleteById(1L);
    }

    @Test
    public void whenDeleteGameSong_thenThrowExceptionIfNotFound() {
        when(gameSongRepository.existsById(99L)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> gameSongService.deleteGameSong(99L));
    }

    // **Test: Obtener todos los GameSongs por songId**
    @Test
    public void whenGetGameSongsBySongId_thenReturnListOfGameSongs() {
        Long songId = 1L;

        GameSongEntity gameSong1 = new GameSongEntity();
        gameSong1.setSongId(songId);
        gameSong1.setGameId(2L);

        GameSongEntity gameSong2 = new GameSongEntity();
        gameSong2.setSongId(songId);
        gameSong2.setGameId(3L);

        when(gameSongRepository.findBySongId(songId)).thenReturn(Arrays.asList(gameSong1, gameSong2));

        List<GameSongEntity> gameSongs = gameSongService.getGameSongsBySongId(songId);

        assertThat(gameSongs).hasSize(2);
        assertThat(gameSongs).extracting(GameSongEntity::getGameId)
                .containsExactlyInAnyOrder(2L, 3L);
    }

    // **Test: Obtener todos los GameSongs por gameId**
    @Test
    public void whenGetGameSongsByGameId_thenReturnListOfGameSongs() {
        Long gameId = 1L;

        GameSongEntity gameSong1 = new GameSongEntity();
        gameSong1.setGameId(gameId);
        gameSong1.setSongId(2L);

        GameSongEntity gameSong2 = new GameSongEntity();
        gameSong2.setGameId(gameId);
        gameSong2.setSongId(3L);

        when(gameSongRepository.findByGameId(gameId)).thenReturn(Arrays.asList(gameSong1, gameSong2));

        List<GameSongEntity> gameSongs = gameSongService.getGameSongsByGameId(gameId);

        assertThat(gameSongs).hasSize(2);
        assertThat(gameSongs).extracting(GameSongEntity::getSongId)
                .containsExactlyInAnyOrder(2L, 3L);
    }
}

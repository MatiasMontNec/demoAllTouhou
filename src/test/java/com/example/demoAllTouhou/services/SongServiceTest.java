package com.example.demoAllTouhou.services;

import com.example.demoAllTouhou.entities.SongEntity;
import com.example.demoAllTouhou.repositories.SongRepository;
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
public class SongServiceTest {

    @InjectMocks
    private SongService songService;

    @Mock
    private SongRepository songRepository;

    // **Test: Crear una nueva Song**
    @Test
    public void whenCreateSong_thenReturnSongEntity() {
        SongEntity song = new SongEntity();
        song.setTitle("Song Title");
        song.setArtist("Song Artist");
        song.setDescription("Song Description");
        song.setLyrics("Song Lyrics");

        when(songRepository.save(any(SongEntity.class))).thenReturn(song);

        SongEntity createdSong = songService.createSong(song);

        assertEquals("Song Title", createdSong.getTitle());
        assertEquals("Song Artist", createdSong.getArtist());
        assertEquals("Song Description", createdSong.getDescription());
        assertEquals("Song Lyrics", createdSong.getLyrics());
    }

    // **Test: Obtener todas las Songs**
    @Test
    public void whenGetAllSongs_thenReturnListOfSongs() {
        SongEntity song1 = new SongEntity();
        song1.setTitle("Song 1");
        song1.setArtist("Artist 1");

        SongEntity song2 = new SongEntity();
        song2.setTitle("Song 2");
        song2.setArtist("Artist 2");

        when(songRepository.findAll()).thenReturn(Arrays.asList(song1, song2));

        List<SongEntity> songs = songService.getAllSongs();

        assertThat(songs).hasSize(2);
        assertThat(songs).extracting(SongEntity::getTitle)
                .containsExactlyInAnyOrder("Song 1", "Song 2");
        assertThat(songs).extracting(SongEntity::getArtist)
                .containsExactlyInAnyOrder("Artist 1", "Artist 2");
    }

    // **Test: Obtener una Song por ID**
    @Test
    public void whenGetSongById_thenReturnSongEntity() {
        SongEntity song = new SongEntity();
        song.setId(1L);
        song.setTitle("Song Title");
        song.setArtist("Song Artist");

        when(songRepository.findById(1L)).thenReturn(Optional.of(song));

        SongEntity result = songService.getSongById(1L);

        assertEquals(1L, result.getId());
        assertEquals("Song Title", result.getTitle());
        assertEquals("Song Artist", result.getArtist());
    }

    @Test
    public void whenGetSongById_thenThrowExceptionIfNotFound() {
        when(songRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> songService.getSongById(99L));
    }

    // **Test: Actualizar una Song**
    @Test
    public void whenUpdateSong_thenReturnUpdatedSongEntity() {
        SongEntity existingSong = new SongEntity();
        existingSong.setId(1L);
        existingSong.setTitle("Old Title");
        existingSong.setArtist("Old Artist");
        existingSong.setDescription("Old Description");
        existingSong.setLyrics("Old Lyrics");

        SongEntity updatedSong = new SongEntity();
        updatedSong.setTitle("New Title");
        updatedSong.setArtist("New Artist");
        updatedSong.setDescription("New Description");
        updatedSong.setLyrics("New Lyrics");

        when(songRepository.findById(1L)).thenReturn(Optional.of(existingSong));
        when(songRepository.save(any(SongEntity.class))).thenReturn(updatedSong);

        SongEntity result = songService.updateSong(1L, updatedSong);

        assertEquals("New Title", result.getTitle());
        assertEquals("New Artist", result.getArtist());
        assertEquals("New Description", result.getDescription());
        assertEquals("New Lyrics", result.getLyrics());
    }

    @Test
    public void whenUpdateSong_thenThrowExceptionIfNotFound() {
        when(songRepository.findById(99L)).thenReturn(Optional.empty());

        SongEntity updatedSong = new SongEntity();
        updatedSong.setTitle("New Title");
        updatedSong.setArtist("New Artist");

        assertThrows(RuntimeException.class, () -> songService.updateSong(99L, updatedSong));
    }

    // **Test: Eliminar una Song por ID**
    @Test
    public void whenDeleteSong_thenDoNothingIfExists() {
        when(songRepository.existsById(1L)).thenReturn(true);

        songService.deleteSong(1L);

        verify(songRepository, times(1)).deleteById(1L);
    }

    @Test
    public void whenDeleteSong_thenThrowExceptionIfNotFound() {
        when(songRepository.existsById(99L)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> songService.deleteSong(99L));
    }

    // **Test: Obtener Songs cuyo título contiene un texto**
    @Test
    public void whenGetSongsByTitle_thenReturnListOfSongs() {
        String title = "Song";

        SongEntity song1 = new SongEntity();
        song1.setTitle("Song 1");
        song1.setArtist("Artist 1");

        SongEntity song2 = new SongEntity();
        song2.setTitle("Song 2");
        song2.setArtist("Artist 2");

        when(songRepository.findByTitleContainingIgnoreCase(title)).thenReturn(Arrays.asList(song1, song2));

        List<SongEntity> songs = songService.getSongsByTitle(title);

        assertThat(songs).hasSize(2);
        assertThat(songs).extracting(SongEntity::getTitle)
                .containsExactlyInAnyOrder("Song 1", "Song 2");
    }

    // **Test: Obtener Songs cuyo artista contiene un texto**
    @Test
    public void whenGetSongsByArtist_thenReturnListOfSongs() {
        String artist = "Artist";

        SongEntity song1 = new SongEntity();
        song1.setTitle("Song 1");
        song1.setArtist("Artist 1");

        SongEntity song2 = new SongEntity();
        song2.setTitle("Song 2");
        song2.setArtist("Artist 2");

        when(songRepository.findByArtistContainingIgnoreCase(artist)).thenReturn(Arrays.asList(song1, song2));

        List<SongEntity> songs = songService.getSongsByArtist(artist);

        assertThat(songs).hasSize(2);
        assertThat(songs).extracting(SongEntity::getArtist)
                .containsExactlyInAnyOrder("Artist 1", "Artist 2");
    }
}

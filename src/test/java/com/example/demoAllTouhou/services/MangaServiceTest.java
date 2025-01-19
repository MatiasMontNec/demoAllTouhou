package com.example.demoAllTouhou.services;

import com.example.demoAllTouhou.entities.MangaEntity;
import com.example.demoAllTouhou.repositories.MangaRepository;
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
public class MangaServiceTest {

    @InjectMocks
    private MangaService mangaService;

    @Mock
    private MangaRepository mangaRepository;

    // **Test: Crear un nuevo Manga**
    @Test
    public void whenCreateManga_thenReturnMangaEntity() {
        MangaEntity manga = new MangaEntity();
        manga.setTitle("Manga Title");
        manga.setAuthor("Manga Author");
        manga.setDescription("Manga Description");
        manga.setAccessLink("http://example.com");

        when(mangaRepository.save(any(MangaEntity.class))).thenReturn(manga);

        MangaEntity createdManga = mangaService.createManga(manga);

        assertEquals("Manga Title", createdManga.getTitle());
        assertEquals("Manga Author", createdManga.getAuthor());
        assertEquals("Manga Description", createdManga.getDescription());
        assertEquals("http://example.com", createdManga.getAccessLink());
    }

    // **Test: Obtener todos los Mangas**
    @Test
    public void whenGetAllMangas_thenReturnListOfMangas() {
        MangaEntity manga1 = new MangaEntity();
        manga1.setTitle("Manga 1");
        manga1.setAuthor("Author 1");

        MangaEntity manga2 = new MangaEntity();
        manga2.setTitle("Manga 2");
        manga2.setAuthor("Author 2");

        when(mangaRepository.findAll()).thenReturn(Arrays.asList(manga1, manga2));

        List<MangaEntity> mangas = mangaService.getAllMangas();

        assertThat(mangas).hasSize(2);
        assertThat(mangas).extracting(MangaEntity::getTitle)
                .containsExactlyInAnyOrder("Manga 1", "Manga 2");
        assertThat(mangas).extracting(MangaEntity::getAuthor)
                .containsExactlyInAnyOrder("Author 1", "Author 2");
    }

    // **Test: Obtener un Manga por ID**
    @Test
    public void whenGetMangaById_thenReturnMangaEntity() {
        MangaEntity manga = new MangaEntity();
        manga.setId(1L);
        manga.setTitle("Manga Title");

        when(mangaRepository.findById(1L)).thenReturn(Optional.of(manga));

        MangaEntity result = mangaService.getMangaById(1L);

        assertEquals(1L, result.getId());
        assertEquals("Manga Title", result.getTitle());
    }

    @Test
    public void whenGetMangaById_thenThrowExceptionIfNotFound() {
        when(mangaRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> mangaService.getMangaById(99L));
    }

    // **Test: Actualizar un Manga**
    @Test
    public void whenUpdateManga_thenReturnUpdatedMangaEntity() {
        MangaEntity existingManga = new MangaEntity();
        existingManga.setId(1L);
        existingManga.setTitle("Old Title");
        existingManga.setAuthor("Old Author");
        existingManga.setDescription("Old Description");

        MangaEntity updatedManga = new MangaEntity();
        updatedManga.setTitle("New Title");
        updatedManga.setAuthor("New Author");
        updatedManga.setDescription("New Description");
        updatedManga.setAccessLink("http://newexample.com");

        when(mangaRepository.findById(1L)).thenReturn(Optional.of(existingManga));
        when(mangaRepository.save(any(MangaEntity.class))).thenReturn(updatedManga);

        MangaEntity result = mangaService.updateManga(1L, updatedManga);

        assertEquals("New Title", result.getTitle());
        assertEquals("New Author", result.getAuthor());
        assertEquals("New Description", result.getDescription());
        assertEquals("http://newexample.com", result.getAccessLink());
    }

    @Test
    public void whenUpdateManga_thenThrowExceptionIfNotFound() {
        when(mangaRepository.findById(99L)).thenReturn(Optional.empty());

        MangaEntity updatedManga = new MangaEntity();
        updatedManga.setTitle("New Title");
        updatedManga.setAuthor("New Author");

        assertThrows(RuntimeException.class, () -> mangaService.updateManga(99L, updatedManga));
    }

    // **Test: Eliminar un Manga por ID**
    @Test
    public void whenDeleteManga_thenDoNothingIfExists() {
        when(mangaRepository.existsById(1L)).thenReturn(true);

        mangaService.deleteManga(1L);

        verify(mangaRepository, times(1)).deleteById(1L);
    }

    @Test
    public void whenDeleteManga_thenThrowExceptionIfNotFound() {
        when(mangaRepository.existsById(99L)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> mangaService.deleteManga(99L));
    }

    // **Test: Obtener Mangas por título**
    @Test
    public void whenGetMangasByTitle_thenReturnListOfMangas() {
        MangaEntity manga1 = new MangaEntity();
        manga1.setTitle("Naruto");

        MangaEntity manga2 = new MangaEntity();
        manga2.setTitle("Boruto");

        when(mangaRepository.findByTitleContainingIgnoreCase("Naruto")).thenReturn(List.of(manga1));

        List<MangaEntity> mangas = mangaService.getMangasByTitle("Naruto");

        assertThat(mangas).hasSize(1);
        assertThat(mangas).extracting(MangaEntity::getTitle).containsExactly("Naruto");
    }

    // **Test: Obtener Mangas por autor**
    @Test
    public void whenGetMangasByAuthor_thenReturnListOfMangas() {
        MangaEntity manga = new MangaEntity();
        manga.setAuthor("Masashi Kishimoto");

        when(mangaRepository.findByAuthorContainingIgnoreCase("Kishimoto")).thenReturn(List.of(manga));

        List<MangaEntity> mangas = mangaService.getMangasByAuthor("Kishimoto");

        assertThat(mangas).hasSize(1);
        assertThat(mangas).extracting(MangaEntity::getAuthor).containsExactly("Masashi Kishimoto");
    }
}

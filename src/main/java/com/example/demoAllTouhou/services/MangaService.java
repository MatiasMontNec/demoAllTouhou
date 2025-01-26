package com.example.demoAllTouhou.services;

import com.example.demoAllTouhou.entities.MangaEntity;
import com.example.demoAllTouhou.repositories.MangaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MangaService {

    private final MangaRepository mangaRepository;

    public MangaService(MangaRepository mangaRepository) {
        this.mangaRepository = mangaRepository;
    }

    // **Crear un nuevo Manga**
    public MangaEntity createManga(MangaEntity mangaEntity) {
        return mangaRepository.save(mangaEntity);
    }

    // **Obtener todos los Mangas**
    public List<MangaEntity> getAllMangas() {
        return mangaRepository.findAll();
    }

    // **Obtener un Manga por ID**
    public MangaEntity getMangaById(Long id) {
        return mangaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Manga not found with id: " + id));
    }

    // **Actualizar un Manga**
    public MangaEntity updateManga(Long id, MangaEntity updatedManga) {
        MangaEntity existingManga = mangaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Manga not found with id: " + id));

        if (updatedManga.getTitle() != null && !updatedManga.getTitle().isEmpty()) existingManga.setTitle(updatedManga.getTitle());
        if (updatedManga.getAuthor() != null && !updatedManga.getAuthor().isEmpty()) existingManga.setAuthor(updatedManga.getAuthor());
        if (updatedManga.getDescription() != null && !updatedManga.getDescription().isEmpty()) existingManga.setDescription(updatedManga.getDescription());
        if (updatedManga.getAccessLink() != null && !updatedManga.getAccessLink().isEmpty()) existingManga.setAccessLink(updatedManga.getAccessLink());

        return mangaRepository.save(existingManga);
    }

    // **Eliminar un Manga por ID**
    public void deleteManga(Long id) {
        if (!mangaRepository.existsById(id)) {
            throw new RuntimeException("Manga not found with id: " + id);
        }
        mangaRepository.deleteById(id);
    }

    // **Obtener Mangas por título**
    public List<MangaEntity> getMangasByTitle(String title) {
        return mangaRepository.findByTitleContainingIgnoreCase(title);
    }

    // **Obtener Mangas por autor**
    public List<MangaEntity> getMangasByAuthor(String author) {
        return mangaRepository.findByAuthorContainingIgnoreCase(author);
    }
}

package com.example.demoAllTouhou.services;

import com.example.demoAllTouhou.entities.MangaEntity;
import com.example.demoAllTouhou.repositories.MangaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MangaService {

    @Autowired
    private MangaRepository mangaRepository;

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

        existingManga.setTitle(updatedManga.getTitle());
        existingManga.setAuthor(updatedManga.getAuthor());
        existingManga.setDescription(updatedManga.getDescription());
        existingManga.setAccessLink(updatedManga.getAccessLink());

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

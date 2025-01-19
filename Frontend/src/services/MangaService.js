import httpClient from "../http-common";

// Crea un nuevo Manga
const createManga = (manga) => {
    return httpClient.post(`/mangas`, manga);
};

// Obtiene todos los Mangas
const getAllMangas = () => {
    return httpClient.get(`/mangas`);
};

// Obtiene un Manga por ID
const getMangaById = (id) => {
    return httpClient.get(`/mangas/${id}`);
};

// Actualiza un Manga
const updateManga = (id, manga) => {
    return httpClient.put(`/mangas/${id}`, manga);
};

// Elimina un Manga por ID
const deleteManga = (id) => {
    return httpClient.delete(`/mangas/${id}`);
};

// Obtiene Mangas por título
const getMangasByTitle = (title) => {
    return httpClient.get(`/mangas/by-title`, { params: { title } });
};

// Obtiene Mangas por autor
const getMangasByAuthor = (author) => {
    return httpClient.get(`/mangas/by-author`, { params: { author } });
};

export default {
    createManga,
    getAllMangas,
    getMangaById,
    updateManga,
    deleteManga,
    getMangasByTitle,
    getMangasByAuthor
};

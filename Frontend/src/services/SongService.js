import httpClient from "../http-common";

// Crea una nueva canción
const createSong = (song) => {
    return httpClient.post(`/songs/create`, song);
};

// Obtiene todas las canciones
const getAllSongs = () => {
    return httpClient.get(`/songs/all`);
};

// Obtiene una canción por ID
const getSongById = (id) => {
    return httpClient.get(`/songs/${id}`);
};

// Actualiza una canción
const updateSong = (id, updatedSong) => {
    return httpClient.put(`/songs/${id}`, updatedSong);
};

// Elimina una canción por ID
const deleteSong = (id) => {
    return httpClient.delete(`/songs/${id}`);
};

// Obtiene canciones cuyo título contiene un texto
const getSongsByTitle = (title) => {
    return httpClient.get(`/songs/search/title`, { params: { title } });
};

// Obtiene canciones cuyo artista contiene un texto
const getSongsByArtist = (artist) => {
    return httpClient.get(`/songs/search/artist`, { params: { artist } });
};

// Obtiene canciones por lista de IDs
const getSongsByIds = (idList) => {
    return httpClient.post(`/songs/by-ids`, idList);
};

export default {
    createSong,
    getAllSongs,
    getSongById,
    updateSong,
    deleteSong,
    getSongsByTitle,
    getSongsByArtist,
    getSongsByIds
};

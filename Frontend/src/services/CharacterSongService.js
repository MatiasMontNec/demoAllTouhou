import httpClient from "../http-common";

// Crea una nueva relación Character-Song
const createCharacterSong = (characterSong) => {
    return httpClient.post(`/character-songs`, characterSong);
};

// Obtiene todas las relaciones Character-Song
const getAllCharacterSongs = () => {
    return httpClient.get(`/character-songs`);
};

// Obtiene una relación Character-Song por ID
const getCharacterSongById = (id) => {
    return httpClient.get(`/character-songs/${id}`);
};

// Actualiza una relación Character-Song
const updateCharacterSong = (id, characterSong) => {
    return httpClient.put(`/character-songs/${id}`, characterSong);
};

// Elimina una relación Character-Song por ID
const deleteCharacterSong = (id) => {
    return httpClient.delete(`/character-songs/${id}`);
};

// Obtiene una lista de Character IDs por Song ID
const getCharacterIdsBySongId = (songId) => {
    return httpClient.get(`/character-songs/characters-by-song/${songId}`);
};

// Obtiene una lista de Song IDs por Character ID
const getSongIdsByCharacterId = (characterId) => {
    return httpClient.get(`/character-songs/songs-by-character/${characterId}`);
};

export default {
    createCharacterSong,
    getAllCharacterSongs,
    getCharacterSongById,
    updateCharacterSong,
    deleteCharacterSong,
    getCharacterIdsBySongId,
    getSongIdsByCharacterId
};

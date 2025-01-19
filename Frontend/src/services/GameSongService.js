import httpClient from "../http-common";

// Crea un nuevo GameSong
const createGameSong = (gameSong) => {
    return httpClient.post(`/game-songs`, gameSong);
};

// Obtiene todos los GameSongs
const getAllGameSongs = () => {
    return httpClient.get(`/game-songs`);
};

// Obtiene un GameSong por ID
const getGameSongById = (id) => {
    return httpClient.get(`/game-songs/${id}`);
};

// Actualiza un GameSong
const updateGameSong = (id, gameSong) => {
    return httpClient.put(`/game-songs/${id}`, gameSong);
};

// Elimina un GameSong por ID
const deleteGameSong = (id) => {
    return httpClient.delete(`/game-songs/${id}`);
};

// Obtiene todos los GameSongs por songId
const getGameSongsBySongId = (songId) => {
    return httpClient.get(`/game-songs/by-song/${songId}`);
};

// Obtiene todos los GameSongs por gameId
const getGameSongsByGameId = (gameId) => {
    return httpClient.get(`/game-songs/by-game/${gameId}`);
};

export default {
    createGameSong,
    getAllGameSongs,
    getGameSongById,
    updateGameSong,
    deleteGameSong,
    getGameSongsBySongId,
    getGameSongsByGameId
};

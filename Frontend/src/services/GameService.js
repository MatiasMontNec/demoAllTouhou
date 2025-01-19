import httpClient from "../http-common";

// Crea un nuevo Game
const createGame = (game) => {
    return httpClient.post(`/games`, game);
};

// Obtiene todos los Games
const getAllGames = () => {
    return httpClient.get(`/games`);
};

// Obtiene un Game por ID
const getGameById = (id) => {
    return httpClient.get(`/games/${id}`);
};

// Actualiza un Game
const updateGame = (id, game) => {
    return httpClient.put(`/games/${id}`, game);
};

// Elimina un Game por ID
const deleteGame = (id) => {
    return httpClient.delete(`/games/${id}`);
};

// Obtiene un Game por título
const getGameByTitle = (title) => {
    return httpClient.get(`/games/by-title/${title}`);
};

// Obtiene todos los Games que salieron antes de una fecha
const getGamesBeforeDate = (date) => {
    return httpClient.post(`/games/before-date`, date);
};

// Obtiene todos los Games que salieron después de una fecha
const getGamesAfterDate = (date) => {
    return httpClient.post(`/games/after-date`, date);
};

// Obtiene todos los Games que salieron antes de una fecha con año, mes, día
const getGamesBeforeSpecificDate = (date) => {
    return httpClient.post(`/games/before-specific-date`, date);
};

// Obtiene todos los Games que salieron después de una fecha con año, mes, día
const getGamesAfterSpecificDate = (date) => {
    return httpClient.post(`/games/after-specific-date`, date);
};

export default {
    createGame,
    getAllGames,
    getGameById,
    updateGame,
    deleteGame,
    getGameByTitle,
    getGamesBeforeDate,
    getGamesAfterDate,
    getGamesBeforeSpecificDate,
    getGamesAfterSpecificDate
};

import httpClient from "../http-common";

// Crea un nuevo GameCharacter
const createGameCharacter = (gameCharacter) => {
    return httpClient.post(`/game-characters`, gameCharacter);
};

// Obtiene todos los GameCharacters
const getAllGameCharacters = () => {
    return httpClient.get(`/game-characters`);
};

// Obtiene un GameCharacter por ID
const getGameCharacterById = (id) => {
    return httpClient.get(`/game-characters/${id}`);
};

// Actualiza un GameCharacter
const updateGameCharacter = (id, gameCharacter) => {
    return httpClient.put(`/game-characters/${id}`, gameCharacter);
};

// Elimina un GameCharacter por ID
const deleteGameCharacter = (id) => {
    return httpClient.delete(`/game-characters/${id}`);
};

// Obtiene todos los GameCharacters asociados a un Character ID
const getGameCharactersByCharacterId = (characterId) => {
    return httpClient.get(`/game-characters/by-character/${characterId}`);
};

// Obtiene todos los GameCharacters asociados a un Game ID
const getGameCharactersByGameId = (gameId) => {
    return httpClient.get(`/game-characters/by-game/${gameId}`);
};

export default {
    createGameCharacter,
    getAllGameCharacters,
    getGameCharacterById,
    updateGameCharacter,
    deleteGameCharacter,
    getGameCharactersByCharacterId,
    getGameCharactersByGameId
};

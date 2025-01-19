import httpClient from "../http-common";

// Crea un nuevo MangaCharacter
const createMangaCharacter = (mangaCharacter) => {
    return httpClient.post(`/manga-characters`, mangaCharacter);
};

// Obtiene todos los MangaCharacter
const getAllMangaCharacters = () => {
    return httpClient.get(`/manga-characters`);
};

// Obtiene un MangaCharacter por ID
const getMangaCharacterById = (id) => {
    return httpClient.get(`/manga-characters/${id}`);
};

// Actualiza un MangaCharacter
const updateMangaCharacter = (id, mangaCharacter) => {
    return httpClient.put(`/manga-characters/${id}`, mangaCharacter);
};

// Elimina un MangaCharacter por ID
const deleteMangaCharacter = (id) => {
    return httpClient.delete(`/manga-characters/${id}`);
};

// Obtiene MangaCharacter por mangaId
const getMangaCharactersByMangaId = (mangaId) => {
    return httpClient.get(`/manga-characters/by-manga/${mangaId}`);
};

// Obtiene MangaCharacter por characterId
const getMangaCharactersByCharacterId = (characterId) => {
    return httpClient.get(`/manga-characters/by-character/${characterId}`);
};

export default {
    createMangaCharacter,
    getAllMangaCharacters,
    getMangaCharacterById,
    updateMangaCharacter,
    deleteMangaCharacter,
    getMangaCharactersByMangaId,
    getMangaCharactersByCharacterId
};

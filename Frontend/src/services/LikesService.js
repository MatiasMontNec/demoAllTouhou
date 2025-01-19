import httpClient from "../http-common";

// Crea un nuevo like
const createLike = (like) => {
    return httpClient.post(`/likes`, like);
};

// Obtiene todos los likes
const getAllLikes = () => {
    return httpClient.get(`/likes`);
};

// Obtiene un like por ID
const getLikeById = (id) => {
    return httpClient.get(`/likes/${id}`);
};

// Actualiza un like
const updateLike = (id, like) => {
    return httpClient.put(`/likes/${id}`, like);
};

// Elimina un like por ID
const deleteLike = (id) => {
    return httpClient.delete(`/likes/${id}`);
};

// Busca likes por parte del nombre
const getLikesByNameContains = (name) => {
    return httpClient.get(`/likes/search`, {
        params: { name: name }
    });
};

// Obtiene likes por characterId
const getLikesByCharacterId = (characterId) => {
    return httpClient.get(`/likes/by-character/${characterId}`);
};

export default {
    createLike,
    getAllLikes,
    getLikeById,
    updateLike,
    deleteLike,
    getLikesByNameContains,
    getLikesByCharacterId
};

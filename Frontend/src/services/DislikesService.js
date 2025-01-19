import httpClient from "../http-common";

// Crea un nuevo Dislike
const createDislike = (dislike) => {
    return httpClient.post(`/dislikes`, dislike);
};

// Obtiene todos los Dislikes
const getAllDislikes = () => {
    return httpClient.get(`/dislikes`);
};

// Obtiene un Dislike por ID
const getDislikeById = (id) => {
    return httpClient.get(`/dislikes/${id}`);
};

// Actualiza un Dislike
const updateDislike = (id, dislike) => {
    return httpClient.put(`/dislikes/${id}`, dislike);
};

// Elimina un Dislike por ID
const deleteDislike = (id) => {
    return httpClient.delete(`/dislikes/${id}`);
};

// Obtiene todos los Dislikes asociados a un Character ID
const getDislikesByCharacterId = (characterId) => {
    return httpClient.get(`/dislikes/by-character/${characterId}`);
};

export default {
    createDislike,
    getAllDislikes,
    getDislikeById,
    updateDislike,
    deleteDislike,
    getDislikesByCharacterId
};

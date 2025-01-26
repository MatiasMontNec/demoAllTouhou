import httpClient from "../http-common";

// Crea una nueva imagen
const createImage = (image) => {
    return httpClient.post(`/images`, image);
};

// Obtiene todas las imágenes
const getAllImages = () => {
    return httpClient.get(`/images`);
};

// Obtiene una imagen por ID
const getImageById = (id) => {
    return httpClient.get(`/images/${id}`);
};

// Actualiza una imagen
const updateImage = (id, image) => {
    return httpClient.put(`/images/${id}`, image);
};

// Elimina una imagen por ID
const deleteImage = (id) => {
    return httpClient.delete(`/images/${id}`);
};

// Busca imágenes por parte del nombre
const getImagesByNameContains = (name) => {
    return httpClient.get(`/images/search`, {
        params: { name: name }
    });
};

// Obtiene imágenes por characterId
const getImagesByCharacterId = (characterId) => {
    return httpClient.get(`/images/by-character/${characterId}`);
};

// Obtiene imágenes por gameId
const getImagesByGameId = (gameId) => {
    return httpClient.get(`/images/by-game/${gameId}`);
};

// Obtiene imágenes por merchId
const getImagesByMerchId = (merchId) => {
    return httpClient.get(`/images/by-merch/${merchId}`);
};

export default {
    createImage,
    getAllImages,
    getImageById,
    updateImage,
    deleteImage,
    getImagesByNameContains,
    getImagesByCharacterId,
    getImagesByGameId,
    getImagesByMerchId
};

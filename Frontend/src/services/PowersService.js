import httpClient from "../http-common";

// Crea un nuevo Power
const createPower = (power) => {
    return httpClient.post(`/powers/create`, power);
};

// Obtiene todos los Powers
const getAllPowers = () => {
    return httpClient.get(`/powers/all`);
};

// Obtiene un Power por ID
const getPowerById = (id) => {
    return httpClient.get(`/powers/${id}`);
};

// Actualiza un Power
const updatePower = (id, updatedPower) => {
    return httpClient.put(`/powers/${id}`, updatedPower);
};

// Elimina un Power por ID
const deletePower = (id) => {
    return httpClient.delete(`/powers/${id}`);
};

// Obtiene Powers por Name (contiene string)
const getPowersByName = (name) => {
    return httpClient.get(`/powers/search/name`, { params: { name } });
};

// Obtiene Powers por Character ID
const getPowersByCharacterId = (characterId) => {
    return httpClient.get(`/powers/character/${characterId}`);
};

// Obtiene múltiples Powers por lista de IDs
const getPowersByIds = (idList) => {
    return httpClient.post(`/powers/by-ids`, idList);
};

export default {
    createPower,
    getAllPowers,
    getPowerById,
    updatePower,
    deletePower,
    getPowersByName,
    getPowersByCharacterId,
    getPowersByIds
};

import httpClient from "../http-common";

// Crea una nueva Mercancía
const createmerch = (merch) => {
    return httpClient.post(`/mercancies`, merch);
};

// Obtiene todas las Mercancías
const getAllmerch = () => {
    return httpClient.get(`/mercancies`);
};

// Obtiene una Mercancía por ID
const getmerchById = (id) => {
    return httpClient.get(`/mercancies/${id}`);
};

// Actualiza una Mercancía
const updatemerch = (id, merch) => {
    return httpClient.put(`/mercancies/${id}`, merch);
};

// Elimina una Mercancía por ID
const deletemerch = (id) => {
    return httpClient.delete(`/mercancies/${id}`);
};

// Obtiene Mercancías por Character ID
const getmerchByCharacterId = (characterId) => {
    return httpClient.get(`/mercancies/by-character-id/${characterId}`);
};

// Obtiene Mercancías que contienen un texto en el nombre
const getmerchByName = (name) => {
    return httpClient.get(`/mercancies/by-name`, { params: { name } });
};

// Obtiene Mercancías con precio superior al ingresado
const getmerchByPriceGreaterThan = (price) => {
    return httpClient.get(`/mercancies/by-price-greater-than/${price}`);
};

// Obtiene Mercancías con precio inferior al ingresado
const getmerchByPriceLessThan = (price) => {
    return httpClient.get(`/mercancies/by-price-less-than/${price}`);
};

export default {
    createmerch,
    getAllmerch,
    getmerchById,
    updatemerch,
    deletemerch,
    getmerchByCharacterId,
    getmerchByName,
    getmerchByPriceGreaterThan,
    getmerchByPriceLessThan
};

import httpClient from "../http-common";

// Crea una nueva Mercancía
const createMercancy = (mercancy) => {
    return httpClient.post(`/mercancies`, mercancy);
};

// Obtiene todas las Mercancías
const getAllMercancy = () => {
    return httpClient.get(`/mercancies`);
};

// Obtiene una Mercancía por ID
const getMercancyById = (id) => {
    return httpClient.get(`/mercancies/${id}`);
};

// Actualiza una Mercancía
const updateMercancy = (id, mercancy) => {
    return httpClient.put(`/mercancies/${id}`, mercancy);
};

// Elimina una Mercancía por ID
const deleteMercancy = (id) => {
    return httpClient.delete(`/mercancies/${id}`);
};

// Obtiene Mercancías por Character ID
const getMercancyByCharacterId = (characterId) => {
    return httpClient.get(`/mercancies/by-character-id/${characterId}`);
};

// Obtiene Mercancías que contienen un texto en el nombre
const getMercancyByName = (name) => {
    return httpClient.get(`/mercancies/by-name`, { params: { name } });
};

// Obtiene Mercancías con precio superior al ingresado
const getMercancyByPriceGreaterThan = (price) => {
    return httpClient.get(`/mercancies/by-price-greater-than/${price}`);
};

// Obtiene Mercancías con precio inferior al ingresado
const getMercancyByPriceLessThan = (price) => {
    return httpClient.get(`/mercancies/by-price-less-than/${price}`);
};

export default {
    createMercancy,
    getAllMercancy,
    getMercancyById,
    updateMercancy,
    deleteMercancy,
    getMercancyByCharacterId,
    getMercancyByName,
    getMercancyByPriceGreaterThan,
    getMercancyByPriceLessThan
};

import httpClient from "../http-common";

// **Obtener todos los personajes**
const getAllCharacters = () => {
    return httpClient.get("/api/characters");
};

// **Obtener un personaje por ID**
const getCharacterById = (id) => {
    return httpClient.get(`/api/characters/${id}`);
};

// **Crear un nuevo personaje**
const createCharacter = (characterDTO) => {
    return httpClient.post("/api/characters", characterDTO);
};

// **Actualizar un personaje existente**
const updateCharacter = (id, characterDTO) => {
    return httpClient.put(`/api/characters/${id}`, characterDTO);
};

// **Eliminar un personaje por ID**
const deleteCharacter = (id) => {
    return httpClient.delete(`/api/characters/${id}`);
};

// **Buscar un personaje por nombre**
const getCharacterByName = (name) => {
    return httpClient.get("/api/characters/search/name", { params: { name } });
};

// **Buscar personajes por edad máxima**
const getCharactersByAgeLessThanOrEqual = (age) => {
    return httpClient.get("/api/characters/search/age/max", { params: { age } });
};

// **Buscar personajes por edad mínima**
const getCharactersByAgeGreaterThanOrEqual = (age) => {
    return httpClient.get("/api/characters/search/age/min", { params: { age } });
};

// **Buscar personajes por altura mínima**
const getCharactersByHeightGreaterThan = (height) => {
    return httpClient.get("/api/characters/search/height/min", { params: { height } });
};

// **Buscar personajes por altura máxima**
const getCharactersByHeightLessThan = (height) => {
    return httpClient.get("/api/characters/search/height/max", { params: { height } });
};

// **Buscar personajes por peso mínimo**
const getCharactersByWeightGreaterThan = (weight) => {
    return httpClient.get("/api/characters/search/weight/min", { params: { weight } });
};

// **Buscar personajes por peso máximo**
const getCharactersByWeightLessThan = (weight) => {
    return httpClient.get("/api/characters/search/weight/max", { params: { weight } });
};

// **Buscar personajes que contienen un texto en importantFacts**
const getCharactersByImportantFactsContaining = (keyword) => {
    return httpClient.get("/api/characters/search/important-facts", { params: { keyword } });
};

// **Buscar personajes por especie**
const getCharactersBySpecies = (species) => {
    return httpClient.get("/api/characters/search/species", { params: { species } });
};

export default {
    getAllCharacters,
    getCharacterById,
    createCharacter,
    updateCharacter,
    deleteCharacter,
    getCharacterByName,
    getCharactersByAgeLessThanOrEqual,
    getCharactersByAgeGreaterThanOrEqual,
    getCharactersByHeightGreaterThan,
    getCharactersByHeightLessThan,
    getCharactersByWeightGreaterThan,
    getCharactersByWeightLessThan,
    getCharactersByImportantFactsContaining,
    getCharactersBySpecies
};

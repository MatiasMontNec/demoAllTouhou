import httpClient from "../http-common";

const getRandomCharacter = () => {
    return httpClient.get('/api/characters/random');
};

const getCharacterImageUrl = (characterName, imageName) => {
    return `/api/images/characters/${characterName}/${imageName}`;
};

const extractImageName = (path) => {
    if (!path) return '';
    return path.split('/').pop(); // Extrae "angry.png" de "/images/characters/Reimu_Hakurei/angry.png"
};

const getCharacterById = (id) => {
    return httpClient.get(`/api/characters/${id}`);
};

export default {
    getRandomCharacter,
    getCharacterById,
    extractImageName,
    getCharacterImageUrl
}
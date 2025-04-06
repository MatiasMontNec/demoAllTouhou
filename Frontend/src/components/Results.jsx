import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { Box, Typography, Button, Divider } from '@mui/material';
import characterService from "../services/characterService.js";

const Results = () => {
    const { id1, id2 } = useParams();
    const navigate = useNavigate();
    const [character, setCharacter] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    // Obtener el personaje de la API
    useEffect(() => {
        const fetchCharacter = async () => {
            try {
                setLoading(true);
                const response = await characterService.getCharacterById(id2);
                setCharacter(response.data);
                setLoading(false);
            } catch (err) {
                setError('Error al cargar el personaje');
                setLoading(false);
                console.error(err);
            }
        };

        fetchCharacter();
    }, [id2]);

    // Función para obtener la imagen correcta
    const getImageByTestId = () => {
        if (!character) return '';

        // Extrae el nombre del personaje (Reimu_Hakurei)
        const characterName = character.name.replace(/\s+/g, '_');

        // Obtiene el path correspondiente al test
        let imagePath = '';
        switch(id1) {
            case '1': imagePath = character.imagePath1; break;
            case '2': imagePath = character.imagePath2; break;
            case '3': imagePath = character.imagePath3; break;
            case '4': imagePath = character.imagePath4; break;
            default: imagePath = character.imagePath1;
        }

        // Extrae el nombre del archivo (angry.png)
        const imageName = characterService.extractImageName(imagePath);

        if (!imageName) return '';

        // Construye la URL completa
        return characterService.getCharacterImageUrl(characterName, imageName);
    };

    if (loading) {
        return (
            <Box sx={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh' }}>
                <Typography variant="h4">Cargando resultados...</Typography>
            </Box>
        );
    }

    if (error) {
        return (
            <Box sx={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh' }}>
                <Typography variant="h4" color="error">{error}</Typography>
            </Box>
        );
    }

    if (!character) {
        return (
            <Box sx={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh' }}>
                <Typography variant="h4">No se encontró el personaje</Typography>
            </Box>
        );
    }

    return (
            <Box sx={{
                minHeight: '100vh',
                padding: 4
            }}>
                <Typography variant="h2" gutterBottom align="center" sx={{ marginBottom: 4 }}>
                    ¡Resultados del Test!
                </Typography>

                <Box sx={{
                    display: 'flex',
                    flexDirection: { xs: 'column', md: 'row' },
                    gap: 4,
                    maxWidth: 1200,
                    margin: '0 auto'
                }}>
                {/* Imagen del personaje */}
                <Box sx={{
                    flex: 1,
                    display: 'flex',
                    flexDirection: 'column',
                    alignItems: 'center'
                }}>
                    <Box
                        component="img"
                        src={getImageByTestId()}
                        alt={character.name}
                        sx={{
                            maxWidth: '100%',
                            maxHeight: '500px',
                            borderRadius: 2,
                            boxShadow: 3
                        }}
                    />
                </Box>

                {/* Información del personaje */}
                <Box sx={{
                    flex: 1,
                    display: 'flex',
                    flexDirection: 'column',
                    gap: 2
                }}>
                    <Typography variant="h3">
                        {character.name}
                    </Typography>

                    <Divider sx={{ my: 2 }} />

                    <Typography variant="body1">
                        <strong>Edad:</strong> {character.age || 'Desconocida'}
                    </Typography>

                    <Typography variant="body1">
                        <strong>Género:</strong> {character.gender || 'Desconocido'}
                    </Typography>

                    <Typography variant="body1">
                        <strong>Altura:</strong> {character.height ? `${character.height} cm` : 'Desconocida'}
                    </Typography>

                    <Typography variant="body1">
                        <strong>Peso:</strong> {character.weight ? `${character.weight} kg` : 'Desconocido'}
                    </Typography>

                    <Typography variant="body1">
                        <strong>Biografía:</strong> {character.biography}
                    </Typography>

                    <Typography variant="body1">
                        <strong>Reside en:</strong> {character.livesIn}
                    </Typography>

                    <Typography variant="body1">
                        <strong>Especie:</strong> {character.groupSpecies === 1 ? 'Humana' : 'Youkai'}
                    </Typography>

                    <Box sx={{ mt: 4 }}>
                        <Button
                            variant="contained"
                            size="large"
                            onClick={() => navigate('/tests')}
                        >
                            Hacer otro test
                        </Button>
                    </Box>
                </Box>
            </Box>
        </Box>
    );
};

export default Results;
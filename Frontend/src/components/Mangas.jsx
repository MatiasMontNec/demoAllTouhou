import React, { useState } from "react";
import Lottie from "react-lottie";  // Importa Lottie
import {
    Box,
    Typography,
    TextField,
    Button,
    Grid,
    Card,
    CardMedia,
    CardContent,
    IconButton,
} from "@mui/material";
import PersonIcon from "@mui/icons-material/Person";
import BookIcon from "@mui/icons-material/Book";
import animationData from "../animations/waitMangas.json";  // Asegúrate de que la ruta es correcta

export default function Mangas() {
    const [tipoBusqueda, setTipoBusqueda] = useState([]); // Tipos de búsqueda seleccionados
    const [filtros, setFiltros] = useState({}); // Filtros específicos según los tipos de búsqueda seleccionados
    const [resultados, setResultados] = useState([]); // Resultados de búsqueda
    const [query, setQuery] = useState(""); // Texto ingresado en el buscador general
    const [loading, setLoading] = useState(false);  // Estado de carga

    // Manejar selección del tipo de búsqueda (permite seleccionar múltiples opciones)
    const handleTipoBusquedaChange = (tipo) => {
        if (tipoBusqueda.includes(tipo)) {
            setTipoBusqueda(tipoBusqueda.filter((t) => t !== tipo));
        } else {
            setTipoBusqueda([...tipoBusqueda, tipo]);
        }
    };

    // Manejar búsqueda (placeholder para conectar con backend)
    const handleBuscar = () => {
        setLoading(true);  // Activar la carga
        setResultados([]);  // Limpiar los resultados
        const mockResultados = [
            {
                id: 1,
                titulo: "One Piece",
                descripcion: "Un manga sobre piratas en busca del tesoro legendario.",
                autor: "Eiichiro Oda",
                imagen: "https://via.placeholder.com/150",
            },
        ];
        setTimeout(() => {  // Simular tiempo de carga
            setResultados(query || tipoBusqueda.length > 0 ? mockResultados : []);
            setLoading(false);  // Desactivar la carga
        }, 2000);  // Ajusta el tiempo de carga simulado según lo necesario
    };

    // Opciones de animación
    const defaultOptions = {
        loop: true,
        autoplay: true,
        animationData: animationData,  // La animación JSON
        rendererSettings: {
            preserveAspectRatio: "xMidYMid slice",
        },
    };

    return (
        <Box sx={{ padding: 4 }}>
            {/* Buscador General */}
            <Box sx={{ display: "flex", gap: 2, marginBottom: 4 }}>
                <TextField
                    fullWidth
                    label="Buscar por título..."
                    variant="outlined"
                    value={query}
                    onChange={(e) => setQuery(e.target.value)}
                />
                <Button
                    variant="contained"
                    onClick={handleBuscar}
                    disabled={!query && tipoBusqueda.length === 0}
                >
                    Buscar
                </Button>
            </Box>

            <Grid container spacing={4}>
                {/* Filtros */}
                <Grid item xs={3}>
                    <Typography variant="h5" gutterBottom>
                        Filtros
                    </Typography>
                    <Box sx={{ display: "flex", flexDirection: "column", alignItems: "center" }}>
                        {/* Filtro Autor */}
                        <IconButton
                            color={tipoBusqueda.includes("Autor") ? "primary" : "default"}
                            onClick={() => handleTipoBusquedaChange("Autor")}
                            sx={{
                                border: "2px solid",
                                borderColor: tipoBusqueda.includes("Autor") ? "primary.main" : "grey.400",
                                borderRadius: "50%",
                                padding: 2,
                                marginBottom: 2,
                            }}
                        >
                            <BookIcon />
                        </IconButton>
                        <Typography>Autor</Typography>

                        {/* Filtro Personaje */}
                        <IconButton
                            color={tipoBusqueda.includes("Personaje") ? "primary" : "default"}
                            onClick={() => handleTipoBusquedaChange("Personaje")}
                            sx={{
                                border: "2px solid",
                                borderColor: tipoBusqueda.includes("Personaje") ? "primary.main" : "grey.400",
                                borderRadius: "50%",
                                padding: 2,
                                marginBottom: 2,
                            }}
                        >
                            <PersonIcon />
                        </IconButton>
                        <Typography>Personaje</Typography>
                    </Box>

                    {/* Filtros dinámicos */}
                    <Box sx={{ marginTop: 4 }}>
                        {tipoBusqueda.includes("Autor") && (
                            <TextField
                                fullWidth
                                label="Autor del Manga"
                                variant="outlined"
                                sx={{ marginBottom: 2 }}
                                onChange={(e) => setFiltros({ ...filtros, autor: e.target.value })}
                            />
                        )}
                        {tipoBusqueda.includes("Personaje") && (
                            <TextField
                                fullWidth
                                label="Nombre del Personaje"
                                variant="outlined"
                                sx={{ marginBottom: 2 }}
                                onChange={(e) => setFiltros({ ...filtros, personaje: e.target.value })}
                            />
                        )}
                    </Box>
                </Grid>

                {/* Resultados */}
                <Grid item xs={9}>
                    <Typography variant="h5" gutterBottom>
                        Resultados
                    </Typography>
                    <Box sx={{ marginTop: 2 }}>
                        {loading ? (  // Mostrar animación de carga
                            <Lottie options={defaultOptions} height={200} width={200} />
                        ) : resultados.length === 0 ? (
                            <Typography variant="body1">Sin resultados</Typography>
                        ) : (
                            <Grid container spacing={2}>
                                {resultados.map((resultado) => (
                                    <Grid item xs={12} sm={6} md={4} key={resultado.id}>
                                        <Card>
                                            <CardMedia
                                                component="img"
                                                height="150"
                                                image={resultado.imagen}
                                                alt={resultado.titulo}
                                            />
                                            <CardContent>
                                                <Typography variant="h6">{resultado.titulo}</Typography>
                                                <Typography variant="body2">{resultado.descripcion}</Typography>
                                            </CardContent>
                                        </Card>
                                    </Grid>
                                ))}
                            </Grid>
                        )}
                    </Box>
                </Grid>
            </Grid>
        </Box>
    );
}

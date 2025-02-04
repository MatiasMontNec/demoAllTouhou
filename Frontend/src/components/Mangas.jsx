import React, { useEffect, useState } from "react";
import Lottie from "react-lottie";
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
import animationData from "../animations/waitMangas.json";
import { saveToSessionStorage, loadFromSessionStorage } from "../services/sessionStorageService";

export default function Mangas() {
    const [tipoBusqueda, setTipoBusqueda] = useState(() =>
        loadFromSessionStorage("mangas_tipoBusqueda", [])
    );
    const [filtros, setFiltros] = useState(() =>
        loadFromSessionStorage("mangas_filtros", {})
    );
    const [query, setQuery] = useState(() =>
        loadFromSessionStorage("mangas_query", "")
    );
    const [resultados, setResultados] = useState([]); // Todos los resultados
    const [resultadosFiltrados, setResultadosFiltrados] = useState([]); // Resultados filtrados
    const [loading, setLoading] = useState(false);

    // Simular datos iniciales
    const datosIniciales = [
        {
            id: 1,
            titulo: "One Piece",
            descripcion: "Un manga sobre piratas en busca del tesoro legendario.",
            autor: "Eiichiro Oda",
            personaje: "Luffy",
            imagen: "https://via.placeholder.com/150",
        },
        {
            id: 2,
            titulo: "Naruto",
            descripcion: "Un ninja que busca convertirse en Hokage.",
            autor: "Masashi Kishimoto",
            personaje: "Naruto Uzumaki",
            imagen: "https://via.placeholder.com/150",
        },
    ];

    // Cargar datos iniciales al montar
    useEffect(() => {
        setResultados(datosIniciales);
        setResultadosFiltrados(datosIniciales); // Mostrar todos los resultados inicialmente
    }, []);

    // Guardar datos en sessionStorage
    useEffect(() => {
        saveToSessionStorage("mangas_tipoBusqueda", tipoBusqueda);
        saveToSessionStorage("mangas_filtros", filtros);
        saveToSessionStorage("mangas_query", query);
    }, [tipoBusqueda, filtros, query]);

    // Manejar selección del tipo de búsqueda
    const handleTipoBusquedaChange = (tipo) => {
        if (tipoBusqueda.includes(tipo)) {
            setTipoBusqueda(tipoBusqueda.filter((t) => t !== tipo));
        } else {
            setTipoBusqueda([...tipoBusqueda, tipo]);
        }
    };

    // Manejar cambios en los filtros
    useEffect(() => {
        filtrarResultados();
    }, [tipoBusqueda, filtros, query]);

    // Función para filtrar resultados
    const filtrarResultados = () => {
        let resultadosFiltrados = resultados;

        // Filtrar por query (título)
        if (query) {
            resultadosFiltrados = resultadosFiltrados.filter((item) =>
                item.titulo.toLowerCase().includes(query.toLowerCase())
            );
        }

        // Filtrar por tipo de búsqueda (Autor o Personaje)
        if (tipoBusqueda.includes("Autor") && filtros.autor) {
            resultadosFiltrados = resultadosFiltrados.filter((item) =>
                item.autor.toLowerCase().includes(filtros.autor.toLowerCase())
            );
        }
        if (tipoBusqueda.includes("Personaje") && filtros.personaje) {
            resultadosFiltrados = resultadosFiltrados.filter((item) =>
                item.personaje.toLowerCase().includes(filtros.personaje.toLowerCase())
            );
        }

        setResultadosFiltrados(resultadosFiltrados);
    };

    // Opciones de animación
    const defaultOptions = {
        loop: true,
        autoplay: true,
        animationData: animationData,
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
                    onClick={filtrarResultados}
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
                        {loading ? (
                            <Lottie options={defaultOptions} height={200} width={200} />
                        ) : resultadosFiltrados.length === 0 ? (
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
                        ) : (
                            <Grid container spacing={2}>
                                {resultadosFiltrados.map((resultado) => (
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

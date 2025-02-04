import { useState, useEffect } from "react";
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
import MusicNoteIcon from "@mui/icons-material/MusicNote";
import Lottie from "react-lottie";
import waitCancionesAnimation from "../animations/waitCanciones.json";
import { saveToSessionStorage, loadFromSessionStorage } from '../services/sessionStorageService';

export default function Canciones() {
    const [tipoBusqueda, setTipoBusqueda] = useState(() => loadFromSessionStorage("canciones_tipoBusqueda", []));
    const [filtros, setFiltros] = useState(() => loadFromSessionStorage("canciones_filtros", {}));
    const [query, setQuery] = useState(() => loadFromSessionStorage("canciones_query", ""));
    const [resultados, setResultados] = useState([]); // Todos los resultados
    const [resultadosFiltrados, setResultadosFiltrados] = useState([]); // Resultados filtrados
    const [cargando, setCargando] = useState(false);

    // Simular datos iniciales (canciones)
    const datosIniciales = [
        {
            id: 1,
            titulo: "Shape of You",
            descripcion: "Canción de Ed Sheeran del álbum Divide.",
            artista: "Ed Sheeran",
            personaje: "Reimu Hakurei",
            imagen: "https://via.placeholder.com/150",
        },
        {
            id: 2,
            titulo: "Perfect Cherry Blossom",
            descripcion: "Tema principal de Touhou 7.",
            artista: "ZUN",
            personaje: "Marisa Kirisame",
            imagen: "https://via.placeholder.com/150",
        },
    ];

    // Cargar datos iniciales al montar el componente
    useEffect(() => {
        setResultados(datosIniciales);
        setResultadosFiltrados(datosIniciales); // Mostrar todos los resultados al inicio
    }, []);

    // Guardar en sessionStorage
    useEffect(() => {
        saveToSessionStorage("canciones_tipoBusqueda", tipoBusqueda);
        saveToSessionStorage("canciones_filtros", filtros);
        saveToSessionStorage("canciones_query", query);
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

        // Filtrar por query (búsqueda por título)
        if (query) {
            resultadosFiltrados = resultadosFiltrados.filter((item) =>
                item.titulo.toLowerCase().includes(query.toLowerCase())
            );
        }

        // Filtrar por tipo de búsqueda (Artista o Personaje)
        if (tipoBusqueda.includes("Artista") && filtros.artista) {
            resultadosFiltrados = resultadosFiltrados.filter((item) =>
                item.artista.toLowerCase().includes(filtros.artista.toLowerCase())
            );
        }
        if (tipoBusqueda.includes("Personaje") && filtros.personaje) {
            resultadosFiltrados = resultadosFiltrados.filter((item) =>
                item.personaje.toLowerCase().includes(filtros.personaje.toLowerCase())
            );
        }

        setResultadosFiltrados(resultadosFiltrados);
    };

    // Configuración de Lottie para la animación
    const defaultOptions = {
        loop: true,
        autoplay: true,
        animationData: waitCancionesAnimation,
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
                        {/* Filtro Artista */}
                        <IconButton
                            color={tipoBusqueda.includes("Artista") ? "primary" : "default"}
                            onClick={() => handleTipoBusquedaChange("Artista")}
                            sx={{
                                border: "2px solid",
                                borderColor: tipoBusqueda.includes("Artista") ? "primary.main" : "grey.400",
                                borderRadius: "50%",
                                padding: 2,
                                marginBottom: 2,
                            }}
                        >
                            <MusicNoteIcon />
                        </IconButton>
                        <Typography>Artista</Typography>

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
                        {tipoBusqueda.includes("Artista") && (
                            <TextField
                                fullWidth
                                label="Nombre del Artista"
                                variant="outlined"
                                sx={{ marginBottom: 2 }}
                                onChange={(e) => setFiltros({ ...filtros, artista: e.target.value })}
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
                        {cargando ? (
                            <Lottie options={defaultOptions} height={150} width={150} />
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
import {useEffect, useState} from "react";
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
import MusicNoteIcon from "@mui/icons-material/MusicNote";
import { saveToSessionStorage, loadFromSessionStorage } from '../services/sessionStorageService';

// Importa la animación
import waitCancionesAnimation from "../animations/waitCanciones.json";

export default function Canciones() {
    const [tipoBusqueda, setTipoBusqueda] = useState(() => loadFromSessionStorage("canciones_tipoBusqueda", []));
    const [filtros, setFiltros] = useState(() => loadFromSessionStorage("canciones_filtros", {}));
    const [query, setQuery] = useState(() => loadFromSessionStorage("canciones_query", ""));
    const [resultados, setResultados] = useState(() => loadFromSessionStorage("canciones_resultados", []));

    const [cargando, setCargando] = useState(false);

    useEffect(() => {
        saveToSessionStorage("canciones_tipoBusqueda", tipoBusqueda);
        saveToSessionStorage("canciones_filtros", filtros);
        saveToSessionStorage("canciones_query", query);
        saveToSessionStorage("canciones_resultados", resultados);
    }, [tipoBusqueda, filtros, query, resultados]);

    // Manejar selección del tipo de búsqueda (permite seleccionar múltiples opciones)
    const handleTipoBusquedaChange = (tipo) => {
        if (tipoBusqueda.includes(tipo)) {
            setTipoBusqueda(tipoBusqueda.filter((t) => t !== tipo));
        } else {
            setTipoBusqueda([...tipoBusqueda, tipo]);
        }
    };

    // Manejar búsqueda (simulación con datos mock para conectar con backend)
    const handleBuscar = () => {
        setCargando(true); // Empieza a cargar

        // Simulación de búsqueda asincrónica
        setTimeout(() => {
            const mockResultados = [
                {
                    id: 1,
                    titulo: "Shape of You",
                    descripcion: "Canción de Ed Sheeran del álbum Divide.",
                    artista: "Ed Sheeran",
                    imagen: "https://via.placeholder.com/150",
                },
            ];
            setResultados(query || tipoBusqueda.length > 0 ? mockResultados : []);
            setCargando(false); // Termina de cargar
        }, 2000); // Simulación de carga (2 segundos)
    };

    // Configuración de Lottie para la animación
    const defaultOptions = {
        loop: true,
        autoplay: true, // Reproduce la animación automáticamente
        animationData: waitCancionesAnimation, // La animación que se debe mostrar
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

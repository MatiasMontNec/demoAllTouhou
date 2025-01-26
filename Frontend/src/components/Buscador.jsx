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
import SportsEsportsIcon from "@mui/icons-material/SportsEsports";
import waitAnimation from "../animations/waitBuscador.json";
import Lottie from "react-lottie"; // Importar animación
import { saveToSessionStorage, loadFromSessionStorage } from '../services/sessionStorageService';

export default function Buscador() {
    const [tipoBusqueda, setTipoBusqueda] = useState(() => loadFromSessionStorage("buscador_tipoBusqueda", []));
    const [filtros, setFiltros] = useState(() => loadFromSessionStorage("buscador_filtros", {}));
    const [query, setQuery] = useState(() => loadFromSessionStorage("buscador_query", ""));
    const [resultados, setResultados] = useState(() => loadFromSessionStorage("buscador_resultados", []));


    const [isLoading, setIsLoading] = useState(false);

    useEffect(() => {
        saveToSessionStorage("buscador_tipoBusqueda", tipoBusqueda);
        saveToSessionStorage("buscador_filtros", filtros);
        saveToSessionStorage("buscador_query", query);
        saveToSessionStorage("buscador_resultados", resultados);
    }, [tipoBusqueda, filtros, query, resultados]);

    // Manejar selección del tipo de búsqueda
    const handleTipoBusquedaChange = (tipo) => {
        setTipoBusqueda(tipo);
        setFiltros({}); // Limpiar filtros al cambiar tipo de búsqueda
    };

    // Manejar búsqueda (simular conexión con backend)
    const handleBuscar = () => {
        setIsLoading(true); // Activar estado de carga
        setResultados([]); // Limpiar resultados mientras se cargan

        // Simular una llamada a la API
        setTimeout(() => {
            const mockResultados = [
                {
                    id: 1,
                    tipo: tipoBusqueda,
                    titulo: tipoBusqueda === "Personaje" ? "Reimu Hakurei" : "Touhou 6",
                    descripcion: "Descripción de ejemplo.",
                    imagen: "https://via.placeholder.com/150",
                },
            ];
            setResultados(query ? mockResultados : []);
            setIsLoading(false); // Desactivar estado de carga
        }, 2000); // Simular retraso de 2 segundos
    };

    const defaultOptions = {
        loop: true,
        autoplay: true,
        animationData: waitAnimation, // Animación Lottie
        rendererSettings: {
            preserveAspectRatio: "xMidYMid slice",
        },
    };

    return (
        <Box sx={{ padding: 4 }}>
            {/* Buscador */}
            <Box sx={{ display: "flex", gap: 2, marginBottom: 4 }}>
                <TextField
                    fullWidth
                    label="Buscar..."
                    variant="outlined"
                    value={query}
                    onChange={(e) => setQuery(e.target.value)}
                />
                <Button
                    variant="contained"
                    onClick={handleBuscar}
                    disabled={!query}
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
                        <IconButton
                            color={tipoBusqueda === "Personaje" ? "primary" : "default"}
                            onClick={() => handleTipoBusquedaChange("Personaje")}
                            sx={{
                                border: "2px solid",
                                borderColor: tipoBusqueda === "Personaje" ? "primary.main" : "grey.400",
                                borderRadius: "50%",
                                padding: 2,
                                marginBottom: 2,
                            }}
                        >
                            <PersonIcon />
                        </IconButton>
                        <Typography>Personaje</Typography>

                        <IconButton
                            color={tipoBusqueda === "Juego" ? "primary" : "default"}
                            onClick={() => handleTipoBusquedaChange("Juego")}
                            sx={{
                                border: "2px solid",
                                borderColor: tipoBusqueda === "Juego" ? "primary.main" : "grey.400",
                                borderRadius: "50%",
                                padding: 2,
                                marginBottom: 2,
                            }}
                        >
                            <SportsEsportsIcon />
                        </IconButton>
                        <Typography>Juego</Typography>
                    </Box>

                    {/* Filtros dinámicos */}
                    <Box sx={{ marginTop: 4 }}>
                        {tipoBusqueda === "Personaje" && (
                            <Box>
                                <TextField
                                    fullWidth
                                    label="Nombre"
                                    variant="outlined"
                                    sx={{ marginBottom: 2 }}
                                    value={filtros.nombre || ""}
                                    onChange={(e) => setFiltros({ ...filtros, nombre: e.target.value })}
                                />
                                <TextField
                                    fullWidth
                                    label="Edad"
                                    variant="outlined"
                                    sx={{ marginBottom: 2 }}
                                    value={filtros.edad || ""}
                                    onChange={(e) => setFiltros({ ...filtros, edad: e.target.value })}
                                />
                                <TextField
                                    fullWidth
                                    label="Género"
                                    variant="outlined"
                                    value={filtros.genero || ""}
                                    onChange={(e) => setFiltros({ ...filtros, genero: e.target.value })}
                                />
                            </Box>
                        )}

                        {tipoBusqueda === "Juego" && (
                            <Box>
                                <TextField
                                    fullWidth
                                    label="Título"
                                    variant="outlined"
                                    sx={{ marginBottom: 2 }}
                                    value={filtros.titulo || ""}
                                    onChange={(e) => setFiltros({ ...filtros, titulo: e.target.value })}
                                />
                                <TextField
                                    fullWidth
                                    label="Fecha de salida"
                                    type="date"
                                    InputLabelProps={{ shrink: true }}
                                    variant="outlined"
                                    value={filtros.fecha || ""}
                                    onChange={(e) => setFiltros({ ...filtros, fecha: e.target.value })}
                                />
                            </Box>
                        )}
                    </Box>
                </Grid>

                {/* Resultados */}
                <Grid item xs={9}>
                    <Typography variant="h5" gutterBottom>
                        Resultados
                    </Typography>
                    <Box sx={{ marginTop: 2 }}>
                        {isLoading ? (
                            // Mostrar animación mientras se cargan los resultados
                            <Lottie
                                options={defaultOptions}
                                height={300}
                                width={300}
                                style={{ margin: "0 auto" }}
                            />
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

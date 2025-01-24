import { useState } from "react";
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
import MenuBookIcon from "@mui/icons-material/MenuBook";

export default function Buscador() {
    const [tipoBusqueda, setTipoBusqueda] = useState(""); // Principal tipo de búsqueda
    const [filtros, setFiltros] = useState({}); // Filtros específicos según el tipo de búsqueda
    const [resultados, setResultados] = useState([]); // Resultados de búsqueda
    const [query, setQuery] = useState(""); // Texto ingresado en el buscador

    // Manejar selección del tipo de búsqueda
    const handleTipoBusquedaChange = (tipo) => {
        setTipoBusqueda(tipo);
        setFiltros({});
    };

    // Manejar búsqueda (placeholder para conectar con backend)
    const handleBuscar = () => {
        const mockResultados = [
            {
                id: 1,
                tipo: tipoBusqueda,
                titulo: tipoBusqueda === "Personaje" ? "Reimu Hakurei" : tipoBusqueda === "Juego" ? "Touhou 6" : "Manga 1",
                descripcion: "Descripción de ejemplo.",
                imagen: "https://via.placeholder.com/150",
            },
        ];
        setResultados(query ? mockResultados : []);
    };

    return (
        <Box sx={{ padding: 4 }}>
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

                        <IconButton
                            color={tipoBusqueda === "Manga" ? "primary" : "default"}
                            onClick={() => handleTipoBusquedaChange("Manga")}
                            sx={{
                                border: "2px solid",
                                borderColor: tipoBusqueda === "Manga" ? "primary.main" : "grey.400",
                                borderRadius: "50%",
                                padding: 2,
                                marginBottom: 2,
                            }}
                        >
                            <MenuBookIcon />
                        </IconButton>
                        <Typography>Manga</Typography>
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
                                    onChange={(e) => setFiltros({ ...filtros, nombre: e.target.value })}
                                />
                                <TextField
                                    fullWidth
                                    label="Edad"
                                    variant="outlined"
                                    sx={{ marginBottom: 2 }}
                                    onChange={(e) => setFiltros({ ...filtros, edad: e.target.value })}
                                />
                                <TextField
                                    fullWidth
                                    label="Género"
                                    variant="outlined"
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
                                    onChange={(e) => setFiltros({ ...filtros, titulo: e.target.value })}
                                />
                                <TextField
                                    fullWidth
                                    label="Fecha de salida"
                                    type="date"
                                    InputLabelProps={{ shrink: true }}
                                    variant="outlined"
                                    onChange={(e) => setFiltros({ ...filtros, fecha: e.target.value })}
                                />
                            </Box>
                        )}

                        {tipoBusqueda === "Manga" && (
                            <Box>
                                <TextField
                                    fullWidth
                                    label="Título"
                                    variant="outlined"
                                    sx={{ marginBottom: 2 }}
                                    onChange={(e) => setFiltros({ ...filtros, titulo: e.target.value })}
                                />
                                <TextField
                                    fullWidth
                                    label="Autor"
                                    variant="outlined"
                                    onChange={(e) => setFiltros({ ...filtros, autor: e.target.value })}
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
                        {resultados.length === 0 ? (
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

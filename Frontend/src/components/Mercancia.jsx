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
import AttachMoneyIcon from "@mui/icons-material/AttachMoney";
import CalendarTodayIcon from "@mui/icons-material/CalendarToday";
import PersonIcon from "@mui/icons-material/Person";
import Lottie from "react-lottie"; // Importar Lottie para las animaciones
import waitAnimation from "../animations/waitMercancia.json"; // Importar animación

export default function Mercancia() {
    const [tipoBusqueda, setTipoBusqueda] = useState([]); // Tipos de búsqueda seleccionados
    const [filtros, setFiltros] = useState({}); // Filtros específicos según los tipos de búsqueda seleccionados
    const [resultados, setResultados] = useState([]); // Resultados de búsqueda
    const [query, setQuery] = useState(""); // Texto ingresado en el buscador general
    const [loading, setLoading] = useState(false); // Estado de carga

    // Configuración de la animación Lottie
    const defaultOptions = {
        loop: true,
        autoplay: true,
        animationData: waitAnimation,
        rendererSettings: {
            preserveAspectRatio: "xMidYMid slice",
        },
    };

    // Manejar selección del tipo de búsqueda
    const handleTipoBusquedaChange = (tipo) => {
        if (tipoBusqueda.includes(tipo)) {
            setTipoBusqueda(tipoBusqueda.filter((t) => t !== tipo));
        } else {
            setTipoBusqueda([...tipoBusqueda, tipo]);
        }
    };

    // Simular búsqueda con datos mock
    const handleBuscar = () => {
        setLoading(true); // Iniciar estado de carga
        setTimeout(() => {
            const mockResultados = [
                {
                    id: 1,
                    nombre: "Figura de acción",
                    descripcion: "Figura coleccionable de alta calidad.",
                    precio: 29.99,
                    imagen: "https://via.placeholder.com/150",
                },
            ];
            setResultados(query || tipoBusqueda.length > 0 ? mockResultados : []);
            setLoading(false); // Finalizar estado de carga
        }, 2000); // Simular 2 segundos de carga
    };

    return (
        <Box sx={{ padding: 4 }}>
            {/* Buscador General */}
            <Box sx={{ display: "flex", gap: 2, marginBottom: 4 }}>
                <TextField
                    fullWidth
                    label="Buscar por nombre del producto..."
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
                        {/* Filtro Precio */}
                        <IconButton
                            color={tipoBusqueda.includes("Precio") ? "primary" : "default"}
                            onClick={() => handleTipoBusquedaChange("Precio")}
                            sx={{
                                border: "2px solid",
                                borderColor: tipoBusqueda.includes("Precio") ? "primary.main" : "grey.400",
                                borderRadius: "50%",
                                padding: 2,
                                marginBottom: 2,
                            }}
                        >
                            <AttachMoneyIcon />
                        </IconButton>
                        <Typography>Precio</Typography>

                        {/* Filtro Fecha */}
                        <IconButton
                            color={tipoBusqueda.includes("Fecha") ? "primary" : "default"}
                            onClick={() => handleTipoBusquedaChange("Fecha")}
                            sx={{
                                border: "2px solid",
                                borderColor: tipoBusqueda.includes("Fecha") ? "primary.main" : "grey.400",
                                borderRadius: "50%",
                                padding: 2,
                                marginBottom: 2,
                            }}
                        >
                            <CalendarTodayIcon />
                        </IconButton>
                        <Typography>Fecha</Typography>

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
                        {tipoBusqueda.includes("Precio") && (
                            <>
                                <TextField
                                    fullWidth
                                    label="Menor a"
                                    variant="outlined"
                                    sx={{ marginBottom: 2 }}
                                    onChange={(e) =>
                                        setFiltros({ ...filtros, precioMenorA: e.target.value })
                                    }
                                />
                                <TextField
                                    fullWidth
                                    label="Mayor a"
                                    variant="outlined"
                                    sx={{ marginBottom: 2 }}
                                    onChange={(e) =>
                                        setFiltros({ ...filtros, precioMayorA: e.target.value })
                                    }
                                />
                            </>
                        )}
                        {tipoBusqueda.includes("Fecha") && (
                            <>
                                <TextField
                                    fullWidth
                                    label="Desde (YYYY-MM-DD)"
                                    variant="outlined"
                                    sx={{ marginBottom: 2 }}
                                    onChange={(e) => setFiltros({ ...filtros, fechaDesde: e.target.value })}
                                />
                                <TextField
                                    fullWidth
                                    label="Antes de (YYYY-MM-DD)"
                                    variant="outlined"
                                    sx={{ marginBottom: 2 }}
                                    onChange={(e) => setFiltros({ ...filtros, fechaHasta: e.target.value })}
                                />
                            </>
                        )}
                        {tipoBusqueda.includes("Personaje") && (
                            <TextField
                                fullWidth
                                label="Nombre del Personaje"
                                variant="outlined"
                                sx={{ marginBottom: 2 }}
                                onChange={(e) =>
                                    setFiltros({ ...filtros, personaje: e.target.value })
                                }
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
                                                alt={resultado.nombre}
                                            />
                                            <CardContent>
                                                <Typography variant="h6">{resultado.nombre}</Typography>
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

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
    Autocomplete,
} from "@mui/material";
import AttachMoneyIcon from "@mui/icons-material/AttachMoney";
import PersonIcon from "@mui/icons-material/Person";
import Lottie from "react-lottie";
import waitAnimation from "../animations/waitMercancia.json";
import { saveToSessionStorage, loadFromSessionStorage } from '../services/sessionStorageService';

export default function Mercancia() {
    const [tipoBusqueda, setTipoBusqueda] = useState(() => loadFromSessionStorage("mercancia_tipoBusqueda", []));
    const [filtros, setFiltros] = useState(() => loadFromSessionStorage("mercancia_filtros", {}));
    const [query, setQuery] = useState(() => loadFromSessionStorage("mercancia_query", ""));
    const [resultados, setResultados] = useState([]); // Todos los resultados
    const [resultadosFiltrados, setResultadosFiltrados] = useState([]); // Resultados filtrados
    const [loading, setLoading] = useState(false);

    // Simular datos iniciales (mercancías)
    const datosIniciales = [
        {
            id: 1,
            nombre: "Figura de acción",
            descripcion: "Figura coleccionable de alta calidad.",
            precio: 29.99,
            imagen: "https://via.placeholder.com/150",
            personaje: "Reimu Hakurei",
        },
        {
            id: 2,
            nombre: "Poster",
            descripcion: "Poster oficial de Touhou.",
            precio: 15.99,
            imagen: "https://via.placeholder.com/150",
            personaje: "Marisa Kirisame",
        },
    ];

    // Cargar datos iniciales al montar el componente
    useEffect(() => {
        setResultados(datosIniciales);
        setResultadosFiltrados(datosIniciales); // Mostrar todos los resultados al inicio
    }, []);

    // Guardar en sessionStorage
    useEffect(() => {
        saveToSessionStorage("mercancia_tipoBusqueda", tipoBusqueda);
        saveToSessionStorage("mercancia_filtros", filtros);
        saveToSessionStorage("mercancia_query", query);
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

        // Filtrar por query (búsqueda por nombre)
        if (query) {
            resultadosFiltrados = resultadosFiltrados.filter((item) =>
                item.nombre.toLowerCase().includes(query.toLowerCase())
            );
        }

        // Filtrar por tipo de búsqueda (Precio o Personaje)
        if (tipoBusqueda.includes("Precio")) {
            if (filtros.precioMin) {
                resultadosFiltrados = resultadosFiltrados.filter((item) => item.precio >= filtros.precioMin);
            }
            if (filtros.precioMax) {
                resultadosFiltrados = resultadosFiltrados.filter((item) => item.precio <= filtros.precioMax);
            }
        }
        if (tipoBusqueda.includes("Personaje")) {
            if (filtros.personaje) {
                resultadosFiltrados = resultadosFiltrados.filter((item) =>
                    item.personaje.toLowerCase().includes(filtros.personaje.toLowerCase())
                );
            }
        }

        setResultadosFiltrados(resultadosFiltrados);
    };

    // Opciones de precio para el combo box
    const opcionesPrecio = [
        { label: "$0", value: 0 },
        { label: "$10", value: 10 },
        { label: "$20", value: 20 },
        { label: "$50", value: 50 },
        { label: "$100", value: 100 },
        { label: "$200", value: 200 },
        { label: "$500", value: 500 },
        { label: "Desconocido", value: null }, // "Desconocido" se manejará como null
    ];

    return (
        <Box sx={{ padding: 4 }}>
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
                    onClick={filtrarResultados}
                    disabled={!query && tipoBusqueda.length === 0}
                >
                    Buscar
                </Button>
            </Box>

            <Grid container spacing={4}>
                <Grid item xs={3}>
                    <Typography variant="h5" gutterBottom>
                        Filtros
                    </Typography>
                    <Box sx={{ display: "flex", flexDirection: "column", alignItems: "center" }}>
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

                    <Box sx={{ marginTop: 4 }}>
                        {tipoBusqueda.includes("Precio") && (
                            <Box sx={{ marginBottom: 2 }}>
                                <Typography variant="subtitle1" gutterBottom>
                                    Rango de precio en dólares
                                </Typography>
                                <Grid container spacing={2}>
                                    <Grid item xs={6}>
                                        <Autocomplete
                                            fullWidth
                                            options={opcionesPrecio}
                                            value={opcionesPrecio.find((opcion) => opcion.value === filtros.precioMin) || null}
                                            onChange={(event, newValue) => {
                                                setFiltros({ ...filtros, precioMin: newValue ? newValue.value : null });
                                            }}
                                            renderInput={(params) => (
                                                <TextField
                                                    {...params}
                                                    label="Precio mín"
                                                    variant="outlined"
                                                />
                                            )}
                                        />
                                    </Grid>
                                    <Grid item xs={6}>
                                        <Autocomplete
                                            fullWidth
                                            options={opcionesPrecio}
                                            value={opcionesPrecio.find((opcion) => opcion.value === filtros.precioMax) || null}
                                            onChange={(event, newValue) => {
                                                setFiltros({ ...filtros, precioMax: newValue ? newValue.value : null });
                                            }}
                                            renderInput={(params) => (
                                                <TextField
                                                    {...params}
                                                    label="Precio máx"
                                                    variant="outlined"
                                                />
                                            )}
                                        />
                                    </Grid>
                                </Grid>
                            </Box>
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
                        ) : (
                            <Grid container spacing={2}>
                                {resultadosFiltrados.map((resultado) => (
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
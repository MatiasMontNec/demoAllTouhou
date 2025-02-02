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
import PersonIcon from "@mui/icons-material/Person";
import SportsEsportsIcon from "@mui/icons-material/SportsEsports";
import waitAnimation from "../animations/waitBuscador.json";
import Lottie from "react-lottie";
import { saveToSessionStorage, loadFromSessionStorage } from '../services/sessionStorageService';

export default function Buscador() {
    const [tipoBusqueda, setTipoBusqueda] = useState(() => loadFromSessionStorage("buscador_tipoBusqueda", ""));
    const [filtros, setFiltros] = useState(() => loadFromSessionStorage("buscador_filtros", {}));
    const [query, setQuery] = useState(() => loadFromSessionStorage("buscador_query", ""));
    const [resultados, setResultados] = useState([]); // Todos los resultados
    const [resultadosFiltrados, setResultadosFiltrados] = useState([]); // Resultados filtrados
    const [isLoading, setIsLoading] = useState(false);

    // Lista de especies para el filtro
    const especies = [
        "Youkai",
        "Espíritus",
        "Seres divinos y celestiales",
        "Seres humanos y semi-humanos",
        "Criaturas mitológicas y folclóricas",
        "Criaturas animales y tsukumogami",
        "Especies exclusivas de Touhou",
        "Otras clasificaciones",
    ];

    // Simular datos iniciales (personajes y juegos)
    const datosIniciales = [
        { id: 1, tipo: "Personaje", titulo: "Reimu Hakurei", descripcion: "Protagonista de Touhou.", imagen: "https://via.placeholder.com/150", edad: 16, altura: 158, peso: 45, genero: "Mujer", especie: "Humano" },
        { id: 2, tipo: "Personaje", titulo: "Marisa Kirisame", descripcion: "Amiga de Reimu.", imagen: "https://via.placeholder.com/150", edad: 18, altura: 160, peso: 48, genero: "Mujer", especie: "Humano" },
        { id: 3, tipo: "Juego", titulo: "Touhou 6", descripcion: "Embodiment of Scarlet Devil.", imagen: "https://via.placeholder.com/150", año: 2002, genero: "Bullet Hell" },
        { id: 4, tipo: "Juego", titulo: "Touhou 7", descripcion: "Perfect Cherry Blossom.", imagen: "https://via.placeholder.com/150", año: 2003, genero: "Bullet Hell" },
    ];

    // Cargar datos iniciales al montar el componente
    useEffect(() => {
        setResultados(datosIniciales);
        setResultadosFiltrados(datosIniciales); // Mostrar todos los resultados al inicio
    }, []);

    // Guardar en sessionStorage
    useEffect(() => {
        saveToSessionStorage("buscador_tipoBusqueda", tipoBusqueda);
        saveToSessionStorage("buscador_filtros", filtros);
        saveToSessionStorage("buscador_query", query);
    }, [tipoBusqueda, filtros, query]);

    // Manejar selección del tipo de búsqueda
    const handleTipoBusquedaChange = (tipo) => {
        setTipoBusqueda(tipo);
        setFiltros({}); // Limpiar filtros al cambiar tipo de búsqueda
        filtrarResultados(tipo, {}, query); // Aplicar filtro de tipo
    };

    // Manejar cambios en los filtros
    useEffect(() => {
        filtrarResultados(tipoBusqueda, filtros, query);
    }, [tipoBusqueda, filtros, query]);

    // Función para filtrar resultados
    const filtrarResultados = (tipo, filtros, query) => {
        let resultadosFiltrados = resultados;

        // Filtrar por tipo (Personaje o Juego)
        if (tipo) {
            resultadosFiltrados = resultadosFiltrados.filter((item) => item.tipo === tipo);
        }

        // Filtrar por query (búsqueda por nombre)
        if (query) {
            resultadosFiltrados = resultadosFiltrados.filter((item) =>
                item.titulo.toLowerCase().includes(query.toLowerCase())
            );
        }

        // Filtrar por filtros específicos
        if (tipo === "Personaje") {
            if (filtros.edadMin) {
                resultadosFiltrados = resultadosFiltrados.filter((item) => item.edad >= filtros.edadMin);
            }
            if (filtros.edadMax) {
                resultadosFiltrados = resultadosFiltrados.filter((item) => item.edad <= filtros.edadMax);
            }
            if (filtros.genero) {
                resultadosFiltrados = resultadosFiltrados.filter((item) => item.genero === filtros.genero);
            }
            if (filtros.especie) {
                resultadosFiltrados = resultadosFiltrados.filter((item) => item.especie === filtros.especie);
            }
        } else if (tipo === "Juego") {
            if (filtros.anioMin) {
                resultadosFiltrados = resultadosFiltrados.filter((item) => item.año >= filtros.anioMin);
            }
            if (filtros.anioMax) {
                resultadosFiltrados = resultadosFiltrados.filter((item) => item.año <= filtros.anioMax);
            }
            if (filtros.generos) {
                resultadosFiltrados = resultadosFiltrados.filter((item) =>
                    filtros.generos.includes(item.genero)
                );
            }
        }

        setResultadosFiltrados(resultadosFiltrados);
    };

    const defaultOptions = {
        loop: true,
        autoplay: true,
        animationData: waitAnimation, // Animación Lottie
        rendererSettings: {
            preserveAspectRatio: "xMidYMid slice",
        },
    };

    // Opciones de edad para el combo box
    const opcionesEdad = [
        { label: "0 años", value: 0 },
        { label: "10 años", value: 10 },
        { label: "20 años", value: 20 },
        { label: "50 años", value: 50 },
        { label: "100 años", value: 100 },
        { label: "500 años", value: 500 },
        { label: "1000 años", value: 1000 },
        { label: "10,000 años", value: 10000 },
        { label: "Desconocido", value: null }, // "Desconocido" se manejará como null
    ];

    // Opciones de altura para el combo box
    const opcionesAltura = [
        { label: "0 cm", value: 0 },
        { label: "50 cm", value: 50 },
        { label: "100 cm", value: 100 },
        { label: "150 cm", value: 150 },
        { label: "180 cm", value: 180 },
        { label: "200 cm", value: 200 },
        { label: "250 cm", value: 250 },
        { label: "Desconocido", value: null }, // "Desconocido" se manejará como null
    ];

// Opciones de peso para el combo box
    const opcionesPeso = [
        { label: "0 kg", value: 0 },
        { label: "20 kg", value: 20 },
        { label: "50 kg", value: 50 },
        { label: "80 kg", value: 80 },
        { label: "100 kg", value: 100 },
        { label: "150 kg", value: 150 },
        { label: "200 kg", value: 200 },
        { label: "Desconocido", value: null }, // "Desconocido" se manejará como null
    ];

// Componente de filtro de altura y peso
    const FiltroAlturaPeso = ({ filtros, setFiltros }) => {
        return (
            <Box sx={{ marginBottom: 2 }}>
                <Typography variant="subtitle1" gutterBottom>
                    Altura y peso
                </Typography>
                <Grid container spacing={2}>
                    {/* Filtro de altura */}
                    <Grid item xs={6}>
                        <Autocomplete
                            fullWidth
                            options={opcionesAltura}
                            value={opcionesAltura.find((opcion) => opcion.value === filtros.alturaMin) || null}
                            onChange={(event, newValue) => {
                                setFiltros({ ...filtros, alturaMin: newValue ? newValue.value : null });
                            }}
                            renderInput={(params) => (
                                <TextField
                                    {...params}
                                    label="Altura mín"
                                    variant="outlined"
                                />
                            )}
                        />
                    </Grid>
                    <Grid item xs={6}>
                        <Autocomplete
                            fullWidth
                            options={opcionesAltura}
                            value={opcionesAltura.find((opcion) => opcion.value === filtros.alturaMax) || null}
                            onChange={(event, newValue) => {
                                setFiltros({ ...filtros, alturaMax: newValue ? newValue.value : null });
                            }}
                            renderInput={(params) => (
                                <TextField
                                    {...params}
                                    label="Altura máx"
                                    variant="outlined"
                                />
                            )}
                        />
                    </Grid>
                    {/* Filtro de peso */}
                    <Grid item xs={6}>
                        <Autocomplete
                            fullWidth
                            options={opcionesPeso}
                            value={opcionesPeso.find((opcion) => opcion.value === filtros.pesoMin) || null}
                            onChange={(event, newValue) => {
                                setFiltros({ ...filtros, pesoMin: newValue ? newValue.value : null });
                            }}
                            renderInput={(params) => (
                                <TextField
                                    {...params}
                                    label="Peso mín"
                                    variant="outlined"
                                />
                            )}
                        />
                    </Grid>
                    <Grid item xs={6}>
                        <Autocomplete
                            fullWidth
                            options={opcionesPeso}
                            value={opcionesPeso.find((opcion) => opcion.value === filtros.pesoMax) || null}
                            onChange={(event, newValue) => {
                                setFiltros({ ...filtros, pesoMax: newValue ? newValue.value : null });
                            }}
                            renderInput={(params) => (
                                <TextField
                                    {...params}
                                    label="Peso máx"
                                    variant="outlined"
                                />
                            )}
                        />
                    </Grid>
                </Grid>
            </Box>
        );
    };

    // Opciones de años para el combo box
    const opcionesAnios = Array.from({ length: new Date().getFullYear() - 1995 + 1 }, (_, i) => ({
        label: `${1995 + i}`,
        value: 1995 + i,
    }));
    opcionesAnios.push({ label: "Desconocido", value: null }); // Añadir opción "Desconocido"

// Componente de filtro de rango de años
    const FiltroRangoAnios = ({ filtros, setFiltros }) => {
        return (
            <Box sx={{ marginBottom: 2 }}>
                <Typography variant="subtitle1" gutterBottom>
                    Rango de años
                </Typography>
                <Grid container spacing={2}>
                    {/* Año mínimo */}
                    <Grid item xs={6}>
                        <Autocomplete
                            fullWidth
                            options={opcionesAnios}
                            value={opcionesAnios.find((opcion) => opcion.value === filtros.anioMin) || null}
                            onChange={(event, newValue) => {
                                setFiltros({ ...filtros, anioMin: newValue ? newValue.value : null });
                            }}
                            renderInput={(params) => (
                                <TextField
                                    {...params}
                                    label="Año mín"
                                    variant="outlined"
                                />
                            )}
                        />
                    </Grid>
                    {/* Año máximo */}
                    <Grid item xs={6}>
                        <Autocomplete
                            fullWidth
                            options={opcionesAnios}
                            value={opcionesAnios.find((opcion) => opcion.value === filtros.anioMax) || null}
                            onChange={(event, newValue) => {
                                setFiltros({ ...filtros, anioMax: newValue ? newValue.value : null });
                            }}
                            renderInput={(params) => (
                                <TextField
                                    {...params}
                                    label="Año máx"
                                    variant="outlined"
                                />
                            )}
                        />
                    </Grid>
                </Grid>
            </Box>
        );
    };

    const generos = [
        { id: 1, nombre: "Bullet Hell (Danmaku)", icono: "🎯" },
        { id: 2, nombre: "Juegos de Lucha", icono: "🥊" },
        { id: 3, nombre: "Juegos de Rol (RPG)", icono: "⚔️" },
        { id: 4, nombre: "Juegos de Ritmo", icono: "🎵" },
        { id: 5, nombre: "Roguelike", icono: "🔄" },
        { id: 6, nombre: "Estrategia por Turnos", icono: "♟️" },
        { id: 7, nombre: "Visual Novels", icono: "📖" },
        { id: 8, nombre: "Juegos de Puzles", icono: "🧩" },
        { id: 9, nombre: "Juegos de Mesa Digitales", icono: "🎲" },
        { id: 10, nombre: "Estrategia en Tiempo Real", icono: "⏱️" },
        { id: 11, nombre: "Juegos de carreras", icono: "🏎️" },
        { id: 12, nombre: "Otro", icono: "🌐"},
    ];

// Componente de selector de géneros
    const SelectorGeneros = ({ filtros, setFiltros }) => {
        const handleGeneroClick = (genero) => {
            const generosSeleccionados = filtros.generos || [];
            if (generosSeleccionados.includes(genero.id)) {
                // Si ya está seleccionado, lo quitamos
                setFiltros({
                    ...filtros,
                    generos: generosSeleccionados.filter((id) => id !== genero.id),
                });
            } else {
                // Si no está seleccionado, lo añadimos
                setFiltros({
                    ...filtros,
                    generos: [...generosSeleccionados, genero.id],
                });
            }
        };

        return (
            <Box sx={{ marginBottom: 2 }}>
                <Typography variant="subtitle1" gutterBottom sx={{ color: "text.primary" }}>
                    Géneros
                </Typography>
                <Grid container spacing={2}>
                    {generos.map((genero) => (
                        <Grid item key={genero.id} xs={6}> {/* Mostrar 2 iconos por fila */}
                            <IconButton
                                onClick={() => handleGeneroClick(genero)}
                                sx={{
                                    width: "100%", // Ancho completo
                                    height: "100px", // Altura fija
                                    border: filtros.generos?.includes(genero.id)
                                        ? "2px solid #FFEB3B" // Borde amarillo neón si está seleccionado
                                        : "1px solid #424242", // Borde gris si no está seleccionado
                                    borderRadius: "10px",
                                    padding: "10px",
                                    backgroundColor: filtros.generos?.includes(genero.id)
                                        ? "#FFEB3B" // Fondo amarillo neón si está seleccionado
                                        : "transparent",
                                    color: filtros.generos?.includes(genero.id)
                                        ? "#212121" // Texto oscuro si está seleccionado
                                        : "#212121", // Texto blanco si no está seleccionado
                                    "&:hover": {
                                        backgroundColor: filtros.generos?.includes(genero.id)
                                            ? "#FFC107" // Amarillo vibrante al hacer hover
                                            : "#616161", // Gris oscuro al hacer hover
                                    },
                                }}
                            >
                                <Box sx={{ textAlign: "center" }}>
                                    <Typography variant="body1" sx={{ fontSize: "2rem" }}>
                                        {genero.icono}
                                    </Typography>
                                    <Typography variant="caption" sx={{ display: "block", marginTop: 1 }}>
                                        {genero.nombre}
                                    </Typography>
                                </Box>
                            </IconButton>
                        </Grid>
                    ))}
                </Grid>
            </Box>
        );
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
                    onClick={() => filtrarResultados(tipoBusqueda, filtros, query)}
                    disabled={!query && !tipoBusqueda}
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
                                {/* Filtro de nombre */}
                                <TextField
                                    fullWidth
                                    label="Nombre"
                                    variant="outlined"
                                    sx={{ marginBottom: 2 }}
                                    value={filtros.nombre || ""}
                                    onChange={(e) => setFiltros({ ...filtros, nombre: e.target.value })}
                                />

                                {/* Filtro de género */}
                                <Autocomplete
                                    fullWidth
                                    options={["Mujer", "Hombre"]}
                                    value={filtros.genero || null}
                                    onChange={(event, newValue) => {
                                        setFiltros({ ...filtros, genero: newValue });
                                    }}
                                    renderInput={(params) => (
                                        <TextField
                                            {...params}
                                            label="Género"
                                            variant="outlined"
                                            sx={{ marginBottom: 2 }}
                                        />
                                    )}
                                />

                                {/* Filtro de especie */}
                                <Autocomplete
                                    fullWidth
                                    options={especies}
                                    value={filtros.especie || null}
                                    onChange={(event, newValue) => {
                                        setFiltros({ ...filtros, especie: newValue });
                                    }}
                                    renderInput={(params) => (
                                        <TextField
                                            {...params}
                                            label="Especie"
                                            variant="outlined"
                                            sx={{ marginBottom: 2 }}
                                        />
                                    )}
                                />
                                {/* Filtro de edad */}
                                <Box sx={{ marginBottom: 2 }}>
                                    <Typography variant="subtitle1" gutterBottom>
                                        Rango de edad
                                    </Typography>
                                    <Grid container spacing={2}>
                                        <Grid item xs={6}>
                                            <Autocomplete
                                                fullWidth
                                                options={opcionesEdad}
                                                value={opcionesEdad.find((opcion) => opcion.value === filtros.edadMin) || null}
                                                onChange={(event, newValue) => {
                                                    setFiltros({ ...filtros, edadMin: newValue ? newValue.value : null });
                                                }}
                                                renderInput={(params) => (
                                                    <TextField
                                                        {...params}
                                                        label="Edad mín"
                                                        variant="outlined"
                                                    />
                                                )}
                                            />
                                        </Grid>
                                        <Grid item xs={6}>
                                            <Autocomplete
                                                fullWidth
                                                options={opcionesEdad}
                                                value={opcionesEdad.find((opcion) => opcion.value === filtros.edadMax) || null}
                                                onChange={(event, newValue) => {
                                                    setFiltros({ ...filtros, edadMax: newValue ? newValue.value : null });
                                                }}
                                                renderInput={(params) => (
                                                    <TextField
                                                        {...params}
                                                        label="Edad máx"
                                                        variant="outlined"
                                                    />
                                                )}
                                            />
                                        </Grid>
                                    </Grid>
                                </Box>

                                {/* Filtro de altura y peso */}
                                <FiltroAlturaPeso filtros={filtros} setFiltros={setFiltros} />
                            </Box>
                        )}

                        {tipoBusqueda === "Juego" && (
                            <Box>
                                {/* Filtro de título */}
                                <TextField
                                    fullWidth
                                    label="Título"
                                    variant="outlined"
                                    sx={{ marginBottom: 2 }}
                                    value={filtros.titulo || ""}
                                    onChange={(e) => setFiltros({ ...filtros, titulo: e.target.value })}
                                />

                                {/* Filtro de rango de años */}
                                <FiltroRangoAnios filtros={filtros} setFiltros={setFiltros} />

                                {/* Selector de géneros */}
                                <SelectorGeneros filtros={filtros} setFiltros={setFiltros} />
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
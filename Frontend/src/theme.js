import { createTheme } from "@mui/material/styles";

const homeTheme = createTheme({
    palette: {
        primary: { main: "#F5F5F5" }, // Blanco ligeramente grisáceo
        secondary: { main: "#FAFAFA" }, // Blanco suave
        background: { default: "#FFFFFF", paper: "#F8F8F8" },
        text: { primary: "#424242", secondary: "#757575" },
        neon: {
            primary: "#00e5ff", // Celeste neón
            secondary: "#651fff", // Morado neón
        },
    },
});

// Tema para la vista Buscador
const searchTheme = createTheme({
    palette: {
        primary: { main: "#FFD600" }, // Amarillo más vibrante y contrastante
        secondary: { main: "#FFAB00" }, // Amarillo-naranja vibrante
        background: { default: "#212121", paper: "#424242" },
        text: { primary: "#FFD600", secondary: "#424242" },
        neon: {
            primary: "#FFD600", // Amarillo neón más intenso
            secondary: "#FF6D00", // Naranja neón más intenso
        },
    },
});

const merchandiseTheme = createTheme({
    palette: {
        primary: { main: "#9C27B0" }, // Púrpura puro
        secondary: { main: "#2196F3" }, // Celeste claro
        background: { default: "#212121", paper: "#424242" },
        text: { primary: "#e040fb", secondary: "#9C27B0" },
        neon: {
            primary: "#e040fb", // Púrpura neón
            secondary: "#00b0ff", // Azul celeste neón
        },
    },
});

const songsTheme = createTheme({
    palette: {
        primary: { main: "#0288D1" }, // Azul celeste puro
        secondary: { main: "#FFEB3B" }, // Amarillo brillante
        background: { default: "#212121", paper: "#424242" },
        text: { primary: "#00b8d4", secondary: "#0288D1" },
        neon: {
            primary: "#00b8d4", // Azul celeste neón
            secondary: "#ffff00", // Amarillo intenso
        },
    },
});

const testsTheme = createTheme({
    palette: {
        primary: { main: "#FF7043" }, // Naranja brillante
        secondary: { main: "#FFEB3B" }, // Amarillo brillante
        background: { default: "#212121", paper: "#424242" },
        text: { primary: "#ff6e40", secondary: "#FF7043" },
        neon: {
            primary: "#ff6e40", // Naranja neón
            secondary: "#ffee33", // Amarillo neón cálido
        },
    },
});

const mangasTheme = createTheme({
    palette: {
        primary: { main: "#D32F2F" }, // Rojo puro
        secondary: { main: "#E91E63" }, // Rosa fuerte
        background: { default: "#212121", paper: "#424242" },
        text: { primary: "#ff5252", secondary: "#E91E63" },
        neon: {
            primary: "#ff5252", // Rojo neón
            secondary: "#ff4081", // Rosa neón
        },
    },
});

const notFoundTheme = createTheme({
    palette: {
        primary: { main: "#616161" }, // Gris más neutro
        secondary: { main: "#757575" }, // Gris medio
        background: { default: "#212121", paper: "#424242" },
        text: { primary: "#9e9e9e", secondary: "#B0BEC5" },
        neon: {
            primary: "#9e9e9e", // Gris neón suave
            secondary: "#cfd8dc", // Gris claro con un leve efecto neón
        },
    },
});

const pinkTheme = createTheme({
    palette: {
        primary: { main: "#FF4081" }, // Rosa vibrante
        secondary: { main: "#D81B60" }, // Rosa más fuerte
        background: { default: "#212121", paper: "#424242" },
        text: { primary: "#ff80ab", secondary: "#FF4081" },
        neon: {
            primary: "#ff80ab", // Rosa claro neón
            secondary: "#f50057", // Rosa intenso neón
        },
    },
});

const resultsTheme = createTheme({
    palette: {
        primary: { main: "#4CAF50" }, // Verde puro
        secondary: { main: "#8BC34A" }, // Verde lima
        background: { default: "#212121", paper: "#424242" },
        text: { primary: "#69F0AE", secondary: "#4CAF50" },
        neon: {
            primary: "#69F0AE", // Verde neón claro
            secondary: "#00E676", // Verde neón intenso
        },
    },
});

export {
    homeTheme,
    searchTheme,
    merchandiseTheme,
    songsTheme,
    testsTheme,
    mangasTheme,
    notFoundTheme,
    pinkTheme,
    resultsTheme,
};

import { createTheme } from "@mui/material/styles";

// Tema principal para la vista Home
const homeTheme = createTheme({
    palette: {
        primary: { main: "#7EC8E8" },
        secondary: { main: "#81C784" },
        background: { default: "#F0F0F0", paper: "#cae5e8" },
        text: { primary: "#7e93e8", secondary: "#9B59B6" },
    },
});

// Tema para la vista Buscador
const searchTheme = createTheme({
    palette: {
        primary: { main: "#FFC107" },
        secondary: { main: "#FF9800" },
        background: { default: "#FFF8E1", paper: "#FFE0B2" },
        text: { primary: "#5D4037", secondary: "#8D6E63" },
    },
});

// Tema para la vista Mercancía
const merchandiseTheme = createTheme({
    palette: {
        primary: { main: "#8E24AA" },
        secondary: { main: "#F48FB1" },
        background: { default: "#F3E5F5", paper: "#E1BEE7" },
        text: { primary: "#4A148C", secondary: "#6A1B9A" },
    },
});

// Tema para la vista Canciones
const songsTheme = createTheme({
    palette: {
        primary: { main: "#2196F3" },
        secondary: { main: "#64B5F6" },
        background: { default: "#E3F2FD", paper: "#BBDEFB" },
        text: { primary: "#0D47A1", secondary: "#1565C0" },
    },
});

// Tema para la vista Tests
const testsTheme = createTheme({
    palette: {
        primary: { main: "#FF5722" },
        secondary: { main: "#FF7043" },
        background: { default: "#FFEBEE", paper: "#FFCDD2" },
        text: { primary: "#B71C1C", secondary: "#D32F2F" },
    },
});

// Tema para la vista Mangas
const mangasTheme = createTheme({
    palette: {
        primary: { main: "#D32F2F" },
        secondary: { main: "#C2185B" },
        background: { default: "#F3E5AB", paper: "#FFF9E3" },
        text: { primary: "#4E342E", secondary: "#6D4C41" },
    },
});

// Tema para la vista NotFound con tonalidades de grises
const notFoundTheme = createTheme({
    palette: {
        primary: { main: "#B0B0B0" }, // Gris suave para el color primario
        secondary: { main: "#8C8C8C" }, // Gris medio para detalles secundarios
        background: {
            default: "#E0E0E0", // Fondo gris claro
            paper: "#D5D5D5", // Fondo gris muy claro para tarjetas o elementos flotantes
        },
        text: {
            primary: "#424242", // Gris oscuro para texto principal
            secondary: "#757575", // Gris medio para texto secundario
        },
    },
});


const pinkTheme = createTheme({
    palette: {
        primary: { main: "#E91E63" }, // Rosa fuerte
        secondary: { main: "#F06292" }, // Rosa claro
        background: { default: "#FCE4EC", paper: "#F8BBD0" }, // Fondo rosado suave
        text: { primary: "#880E4F", secondary: "#AD1457" }, // Texto rosado oscuro
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
};

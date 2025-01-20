import { createTheme } from "@mui/material/styles";

// Tema principal para la vista Home
const homeTheme = createTheme({
    palette: {
        primary: { main: "#7EC8E8" }, // Azul claro para serenidad y neutralidad
        secondary: { main: "#81C784" }, // Verde para armonía
        background: { default: "#F0F0F0", paper: "#cae5e8" },
        text: { primary: "#7e93e8", secondary: "#9B59B6" },
    },
});

// Tema para la vista Buscador
const searchTheme = createTheme({
    palette: {
        primary: { main: "#FFC107" }, // Amarillo para estimular la atención
        secondary: { main: "#FF9800" }, // Naranja para creatividad
        background: { default: "#FFF8E1", paper: "#FFE0B2" }, // Colores cálidos para enfoque
        text: { primary: "#5D4037", secondary: "#8D6E63" }, // Tonos marrones para contraste
    },
});

// Tema para la vista Mercancía
const merchandiseTheme = createTheme({
    palette: {
        primary: { main: "#8E24AA" }, // Morado para lujo y exclusividad
        secondary: { main: "#F48FB1" }, // Rosa suave para atractivo visual
        background: { default: "#F3E5F5", paper: "#E1BEE7" }, // Tonos pasteles suaves
        text: { primary: "#4A148C", secondary: "#6A1B9A" },
    },
});

// Tema para la vista Canciones
const songsTheme = createTheme({
    palette: {
        primary: { main: "#2196F3" }, // Azul para serenidad
        secondary: { main: "#64B5F6" }, // Azul claro para energía positiva
        background: { default: "#E3F2FD", paper: "#BBDEFB" }, // Fondos claros y calmantes
        text: { primary: "#0D47A1", secondary: "#1565C0" },
    },
});

// Tema para la vista Tests
const testsTheme = createTheme({
    palette: {
        primary: { main: "#FF5722" }, // Naranja para dinamismo
        secondary: { main: "#FF7043" }, // Naranja más claro para estímulo
        background: { default: "#FFEBEE", paper: "#FFCDD2" }, // Tonos rojizos para energía
        text: { primary: "#B71C1C", secondary: "#D32F2F" },
    },
});

export { homeTheme, searchTheme, merchandiseTheme, songsTheme, testsTheme };

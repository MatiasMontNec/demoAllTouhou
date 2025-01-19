import { createTheme } from "@mui/material/styles";

const theme = createTheme({
    palette: {
        mode: "light", // Puedes cambiar esto a "dark" si prefieres un fondo oscuro
        primary: {
            main: "#7EC8E8", // Azul claro para fondos y áreas de lectura
        },
        secondary: {
            main: "#81C784", // Verde claro para botones y enlaces
        },
        text: {
            primary: "#B23C47", // Rojo oscuro para títulos y textos principales
            secondary: "#9B59B6", // Morado suave para elementos de detalle
        },
        background: {
            default: "#F0F0F0", // Beige para fondo principal
            paper: "#D8D8D8", // Gris claro para tarjetas y contenedores
        },
    },
    components: {
        MuiTextField: {
            styleOverrides: {
                root: {
                    color: "#7EC8E8", // Azul claro para entradas de texto
                },
            },
        },
        MuiButton: {
            styleOverrides: {
                root: {
                    borderRadius: "8px",
                    textTransform: "none", // Mantener el texto en su forma original
                    fontWeight: "bold",
                    fontSize: "1rem",
                },
                containedPrimary: {
                    backgroundColor: "#FF9800", // Naranja para botones de acción
                    color: "#FFFFFF",
                    "&:hover": {
                        backgroundColor: "#FF5722", // Naranja más oscuro para hover
                    },
                },
                containedSecondary: {
                    backgroundColor: "#F8BBD0", // Rosa pastel para botones secundarios
                    color: "#7EC8E8", // Azul claro
                    "&:hover": {
                        backgroundColor: "#F48FB1", // Rosa más oscuro para hover
                    },
                },
            },
        },
    },
});

export default theme;

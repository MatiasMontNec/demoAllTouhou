import React from "react";
import Lottie from "lottie-react";
import animationData from "../animations/notFound.json"; // Asegúrate de colocar el archivo JSON de Lottie en esta ruta
import { Box, Typography } from "@mui/material";

const NotFound = () => {
    return (
        <Box
            sx={{
                display: "flex",
                flexDirection: "column",
                alignItems: "center",
                justifyContent: "center",
                height: "100vh",
                textAlign: "center",
                bgcolor: "#f4f4f4", // Fondo gris claro
                padding: 2,
            }}
        >
            {/* Animación Lottie */}
            <Box
                sx={{
                    width: "200%", // Ajusta el tamaño de la animación
                    maxWidth: "500px", // Tamaño máximo para mantener proporciones en pantallas grandes
                    marginBottom: 4, // Espacio entre la animación y el texto
                }}
            >
                <Lottie animationData={animationData} loop autoplay />
            </Box>

            {/* Mensaje debajo de la animación */}
            <Typography variant="h4" sx={{ color: "#333", marginBottom: 1 }}>
                Página no encontrada
            </Typography>
            <Typography variant="body1" sx={{ color: "#666" }}>
                Lo sentimos, pero no pudimos encontrar lo que buscas.
            </Typography>
        </Box>
    );
};

export default NotFound;

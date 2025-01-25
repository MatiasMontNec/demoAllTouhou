import React, { useState } from "react";
import { useNavigate } from "react-router-dom"; // Importa useNavigate para redirección
import {
    Box,
    Grid,
    Typography,
    Button,
    Card,
    CardMedia,
    CardContent,
} from "@mui/material";
import "../styles.css"; // Mantén el archivo CSS actualizado

// Importamos las imágenes como módulos
import testCharacter from "../Images/TestCharacter.jpg";
import testLike from "../Images/TestLike.jpg";
import testHate from "../Images/TestHate.jpg";
import testPlace from "../Images/TestPlace.jpg";

export default function Tests() {
    const [hovered, setHovered] = useState(null);
    const navigate = useNavigate(); // Inicializa useNavigate para redirección

    const images = [
        {
            src: testCharacter,
            title: "¿Qué personaje de Touhou eres?",
            description:
                "Descubre qué personaje de Touhou representa mejor tu personalidad. Este test se demorará en menos de 5 minutos",
            buttonText: "Comenzar",
            redirectTo: "/Preguntas/1", // Ruta para redirección
        },
        {
            src: testLike,
            title: "¿Qué personaje de Touhou te gustaría?",
            description:
                "Descubre qué personaje de Touhou te gustaría tener como compañero. Este test se demorará en menos de 5 minutos",
            buttonText: "Comenzar",
            redirectTo: "/Preguntas/2", // Ruta para redirección
        },
        {
            src: testHate,
            title: "¿Qué personaje de Touhou te odiaría?",
            description:
                "Descubre qué personaje de Touhou podría no llevarse bien contigo. Este test se demorará en menos de 5 minutos",
            buttonText: "Comenzar",
            redirectTo: "/Preguntas/3", // Ruta para redirección
        },
        {
            src: testPlace,
            title: "¿Qué lugar de Touhou vivirías?",
            description:
                "Descubre el lugar de Touhou que más se adapta a ti. Este test se demorará en menos de 5 minutos",
            buttonText: "Comenzar",
            redirectTo: "/Preguntas/4", // Ruta para redirección
        },
    ];

    return (
        <Box sx={{ padding: 4 }}>
            <Grid container spacing={4}>
                {images.map((image, index) => (
                    <Grid item xs={12} sm={6} key={index}> {/* Dos columnas por fila */}
                        <Card
                            onMouseEnter={() => setHovered(index)}
                            onMouseLeave={() => setHovered(null)}
                            sx={{
                                position: "relative",
                                overflow: "hidden",
                                height: "100%",
                                display: "flex",
                                flexDirection: "column",
                            }}
                        >
                            <CardMedia
                                component="img"
                                alt={image.title}
                                image={image.src}
                                className={
                                    hovered === index
                                        ? "CardMedia CardMediaHovered"
                                        : "CardMedia"
                                }
                                sx={{
                                    height: "100%",
                                    width: "100%",
                                    objectFit: "cover",
                                }}
                            />
                            <CardContent
                                className={
                                    hovered === index
                                        ? "CardContent CardContentHovered"
                                        : "CardContent"
                                }
                                sx={{
                                    position: "absolute",
                                    bottom: 0,
                                    left: 0,
                                    right: 0,
                                    backgroundColor: "rgba(0, 0, 0, 0.5)",
                                    color: "white",
                                    textAlign: "center",
                                    padding: "16px",
                                    transform:
                                        hovered === index
                                            ? "translateY(-30%)"
                                            : "translateY(0)",
                                    transition: "transform 0.3s ease",
                                    height: "100%",
                                    display: "flex",
                                    flexDirection: "column",
                                    justifyContent: "flex-end", // Mantén el contenido al fondo
                                }}
                            >
                                <Typography
                                    variant="h6"
                                    sx={{ fontSize: "1.5rem", fontWeight: "bold" }}
                                >
                                    {image.title}
                                </Typography>
                                {hovered === index && (
                                    <>
                                        <Typography
                                            variant="body2"
                                            sx={{
                                                marginTop: 2,
                                                fontSize: "1.2rem", // Agranda la descripción
                                            }}
                                        >
                                            {image.description}
                                        </Typography>
                                        <Button
                                            variant="contained"
                                            sx={{
                                                marginTop: 2,
                                                fontSize: "1rem", // Ajusta el tamaño del botón
                                                padding: "8px 16px",
                                            }}
                                            onClick={() =>
                                                navigate(image.redirectTo)
                                            } // Redirige a la ruta correspondiente
                                        >
                                            {image.buttonText}
                                        </Button>
                                    </>
                                )}
                            </CardContent>
                        </Card>
                    </Grid>
                ))}
            </Grid>
        </Box>
    );
}

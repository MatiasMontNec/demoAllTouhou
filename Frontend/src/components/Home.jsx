import React, { useState, useEffect } from "react";
import { Box, Typography, Grid, IconButton, Slider } from "@mui/material";
import ArrowBackIosIcon from "@mui/icons-material/ArrowBackIos";
import ArrowForwardIosIcon from "@mui/icons-material/ArrowForwardIos";

// Importa las imágenes locales
import image1 from "../../Images/MEME.jpg";
import image2 from "../../Images/Reimu.jpg";
import image3 from "../../Images/Scarlets.jpg";
import image4 from "../../Images/Yuuka.jpg";

const imageTexts = [
    "Busca acerca de Touhou Project!",
    "Compra acerca de Touhou Project!",
    "Descubre canciones de Touhou Project!",
    "Prueba divertidos Tests de Touhou Project!",
];

const Home = () => {
    const [currentImage, setCurrentImage] = useState(0);

    // Cambiar imagen cada 6 segundos
    useEffect(() => {
        const interval = setInterval(() => {
            setCurrentImage((prev) => (prev + 1) % 4); // Circular
        }, 6000);

        return () => clearInterval(interval); // Limpiar el intervalo al desmontar
    }, []);

    // Cambiar imagen manualmente
    const handleNext = () => {
        setCurrentImage((prev) => (prev + 1) % 4);
    };

    const handlePrevious = () => {
        setCurrentImage((prev) => (prev - 1 + 4) % 4); // Asegura circularidad
    };

    // Cambiar a la imagen seleccionada al hacer clic en la barra deslizante
    const handleSliderChange = (event, newValue) => {
        setCurrentImage(newValue);
    };

    // Array de las imágenes importadas
    const images = [image1, image2, image3, image4];

    return (
        <Box sx={{ textAlign: "center", p: 3 }}>
            {/* Imagen deslizante */}
            <Box sx={{ position: "relative", overflow: "hidden", width: "100%", height: "400px" }}>
                <Grid container spacing={0} sx={{ display: "flex", width: "400%" }}>
                    {/* Mapea las imágenes a las posiciones correspondientes */}
                    {[0, 1, 2, 3].map((index) => (
                        <Grid item xs={3} key={index}>
                            <Box
                                sx={{
                                    position: "relative",
                                    width: "100%",
                                    height: "100%",
                                    backgroundImage: `url(${images[index]})`, // Aquí se asignan las imágenes importadas
                                    backgroundPosition: "center",
                                    backgroundSize: "cover", // Ajusta la imagen para cubrir el contenedor
                                    transition: "transform 1s ease-in-out",
                                    transform: `translateX(-${currentImage * 25}%)`, // Ajuste para el desplazamiento
                                }}
                            >
                                <Typography
                                    variant="h6"
                                    sx={{
                                        position: "absolute",
                                        bottom: "10%",
                                        left: "50%",
                                        transform: "translateX(-50%)",
                                        color: "white",
                                        backgroundColor: "rgba(0, 0, 0, 0.5)",
                                        padding: "10px",
                                    }}
                                >
                                    {imageTexts[index]}
                                </Typography>
                            </Box>
                        </Grid>
                    ))}
                </Grid>
            </Box>

            {/* Barra deslizante */}
            <Box sx={{ mt: 3 }}>
                <Slider
                    value={currentImage}
                    onChange={handleSliderChange}
                    min={0}
                    max={3}
                    step={1}
                    valueLabelDisplay="auto"
                    valueLabelFormat={(value) => `${value + 1}`}
                    sx={{ width: "80%", mx: "auto", color: "primary.main" }}
                />

                {/* Barra de control con números y navegación */}
                <Box sx={{ display: "flex", justifyContent: "space-between", mt: 2, position: "relative" }}>
                    <Box sx={{ position: "absolute", right: 0, bottom: 0, display: "flex", alignItems: "center" }}>
                        <IconButton onClick={handlePrevious}>
                            <ArrowBackIosIcon />
                        </IconButton>
                        <Typography variant="body2" sx={{ mx: 1 }}>
                            {currentImage + 1} / 4
                        </Typography>
                        <IconButton onClick={handleNext}>
                            <ArrowForwardIosIcon />
                        </IconButton>
                    </Box>
                </Box>
            </Box>
        </Box>
    );
};

export default Home;

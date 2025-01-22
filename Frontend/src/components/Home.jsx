import React, { useState, useEffect } from "react";
import { Box, Typography, IconButton, Slider } from "@mui/material";
import ArrowBackIosIcon from "@mui/icons-material/ArrowBackIos";
import ArrowForwardIosIcon from "@mui/icons-material/ArrowForwardIos";

// Importa las imágenes locales
import image1 from "../Images/Reimu.jpg";
import image4 from "../Images/MEME.jpg";
import image3 from "../Images/Scarlets.jpg";
import image2 from "../Images/Yuuka.jpg";
import image5 from "../Images/buscar.jpg";
import image6 from "../Images/mercancía.jpg";
import image7 from "../Images/canciones2.jpg";
import image8 from "../Images/test2.jpg";

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
            {/* Contenedor principal del carrusel */}
            <Box sx={{ position: "relative", overflow: "hidden", width: "100%", height: "400px" }}>
                {/* Imagen actual */}
                <img
                    src={images[currentImage]}
                    alt={`Imagen ${currentImage + 1}`}
                    style={{
                        width: "100%", // Ocupa todo el ancho disponible
                        height: "100%", // Ocupa toda la altura disponible
                        objectFit: "cover", // Asegura que la imagen se recorte correctamente sin distorsionarse
                    }}
                />
                {/* Texto sobre la imagen */}
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
                    {imageTexts[currentImage]}
                </Typography>
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

            <Box sx={{ display: "flex", justifyContent: "space-between", flexWrap: "wrap", gap: 3, mt: 4 }}>
                {[
                    { image: image5, link: "/buscador" },
                    { image: image6, link: "/mercancia" },
                    { image: image7, link: "/canciones" },
                    { image: image8, link: "/tests" },
                ].map((item, index) => (
                    <Box
                        key={index}
                        onClick={() => window.location.href = item.link} // Redirigir
                        sx={{
                            width: 180,
                            height: 180,
                            overflow: "hidden",
                            borderRadius: 10, // Esquinas ligeramente redondeadas
                            cursor: "pointer",
                            position: "relative",
                            transition: "box-shadow 0.3s ease",
                            "&:hover": {
                                boxShadow: "0 4px 15px rgba(0, 0, 0, 0.3)", // Efecto sombra
                            },
                            "&:hover img": {
                                transform: "scale(1.2)", // Agrandar la imagen
                                transition: "transform 0.3s ease",
                            },
                        }}
                    >
                        <img
                            src={item.image}
                            alt={`Cuadro ${index + 1}`}
                            style={{
                                width: "100%",
                                height: "100%",
                                objectFit: "cover",
                            }}
                        />
                    </Box>
                ))}
            </Box>

        </Box>
    );
};

export default Home;

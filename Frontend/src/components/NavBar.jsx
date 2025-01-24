import { useState, useEffect } from "react";
import { AppBar, Box, Toolbar, Typography, Button, IconButton } from "@mui/material";
import { useNavigate } from "react-router-dom";
import logo from "../assets/Touhou101.png"; // Logo de la aplicación
import ArrowUpwardIcon from '@mui/icons-material/ArrowUpward';

export default function Navbar() {
    const navigate = useNavigate();
    const [scrollPosition, setScrollPosition] = useState(0); // Estado para la posición del scroll

    // Función para manejar navegación
    const handleNavigation = (path) => {
        navigate(path);
    };

    // Detectar la posición del scroll al hacer scroll
    useEffect(() => {
        const handleScroll = () => {
            setScrollPosition(window.scrollY);
        };
        window.addEventListener('scroll', handleScroll);

        return () => {
            window.removeEventListener('scroll', handleScroll);
        };
    }, []);

    // Función para ir al top
    const scrollToTop = () => {
        window.scrollTo({
            top: 0,
            behavior: "smooth",
        });
    };

    return (
        <Box sx={{ flexGrow: 1 }}>
            <AppBar position="sticky">
                <Toolbar sx={{ justifyContent: "space-between", padding: "0 2rem" }}>
                    {/* Logo con navegación al Home */}
                    <Typography
                        variant="h6"
                        component="div"
                        sx={{
                            display: "flex",
                            alignItems: "center",
                            cursor: "pointer",
                        }}
                        onClick={() => handleNavigation("/")}
                    >
                        <img
                            src={logo}
                            alt="Touhou101 Logo"
                            style={{
                                height: "40px",
                                marginRight: "1rem",
                            }}
                        />
                    </Typography>

                    {/* Botones del NavBar */}
                    <Box sx={{ display: "flex", alignItems: "center" }}>
                        {/* Botón Buscar que navega directamente */}
                        <Button
                            color="inherit"
                            onClick={() => handleNavigation("/buscador")}
                            sx={{
                                marginRight: 2,
                                "&:hover": {
                                    backgroundColor: "rgba(255, 255, 255, 0.2)",
                                },
                            }}
                        >
                            Buscar
                        </Button>

                        {/* Botones de navegación */}
                        <Button
                            color="inherit"
                            onClick={() => handleNavigation("/mercancia")}
                            sx={{
                                marginRight: 2,
                                "&:hover": {
                                    backgroundColor: "rgba(255, 255, 255, 0.2)",
                                },
                            }}
                        >
                            Ver Mercancía
                        </Button>
                        <Button
                            color="inherit"
                            onClick={() => handleNavigation("/canciones")}
                            sx={{
                                marginRight: 2,
                                "&:hover": {
                                    backgroundColor: "rgba(255, 255, 255, 0.2)",
                                },
                            }}
                        >
                            Ver Canciones
                        </Button>
                        <Button
                            color="inherit"
                            onClick={() => handleNavigation("/tests")}
                            sx={{
                                "&:hover": {
                                    backgroundColor: "rgba(255, 255, 255, 0.2)",
                                },
                            }}
                        >
                            Probar Tests
                        </Button>
                    </Box>
                </Toolbar>
            </AppBar>

            {/* Solo botón para scroll arriba */}
            <Box sx={{ position: 'fixed', bottom: 20, right: 20 }}>
                {scrollPosition > 0 && (
                    <IconButton
                        color="primary"
                        onClick={scrollToTop}
                        sx={{
                            backgroundColor: 'rgba(255, 255, 255, 0.6)',
                            '&:hover': {
                                backgroundColor: 'rgba(255, 255, 255, 0.8)',
                            },
                        }}
                    >
                        <ArrowUpwardIcon />
                    </IconButton>
                )}
            </Box>
        </Box>
    );
}

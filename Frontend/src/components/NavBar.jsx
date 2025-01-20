import { useState } from "react";
import { AppBar, Box, Toolbar, Typography, Button, Menu, MenuItem } from "@mui/material";
import { useNavigate } from "react-router-dom";
import logo from "../assets/Touhou101.png"; // Logo de la aplicación

export default function Navbar() {
    const navigate = useNavigate();
    const [anchorEl, setAnchorEl] = useState(null); // Para manejar el menú desplegable

    // Abrir el menú desplegable
    const handleMenuOpen = (event) => {
        setAnchorEl(event.currentTarget);
    };

    // Cerrar el menú desplegable
    const handleMenuClose = () => {
        setAnchorEl(null);
    };

    // Función para manejar navegación
    const handleNavigation = (path) => {
        navigate(path);
        handleMenuClose(); // Cierra el menú si estaba abierto
    };

    // Función para buscar con un tipo
    const handleSearch = (type) => {
        navigate(`/buscador?type=${type}`); // Envía el parámetro de tipo
        handleMenuClose();
    };

    return (
        <Box sx={{ flexGrow: 1 }}>
            <AppBar position="sticky" >
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
                        {/* Botón Buscar con menú desplegable */}
                        <Button
                            color="inherit"
                            onClick={handleMenuOpen}
                            sx={{
                                marginRight: 2,
                                "&:hover": {
                                    backgroundColor: "rgba(255, 255, 255, 0.2)",
                                },
                            }}
                        >
                            Buscar
                        </Button>
                        <Menu
                            anchorEl={anchorEl}
                            open={Boolean(anchorEl)}
                            onClose={handleMenuClose}
                            anchorOrigin={{
                                vertical: "bottom",
                                horizontal: "left",
                            }}
                            transformOrigin={{
                                vertical: "top",
                                horizontal: "left",
                            }}
                        >
                            <MenuItem onClick={() => handleSearch(0)}>Personaje</MenuItem>
                            <MenuItem onClick={() => handleSearch(1)}>Juego</MenuItem>
                            <MenuItem onClick={() => handleSearch(2)}>Manga</MenuItem>
                        </Menu>

                        {/* Botón Mercancía */}
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

                        {/* Botón Canciones */}
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

                        {/* Botón Tests */}
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
        </Box>
    );
}

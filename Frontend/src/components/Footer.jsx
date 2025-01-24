// src/components/Footer.jsx
import React from "react";
import { Typography, Link, Box, IconButton, Avatar } from "@mui/material";
import InstagramIcon from "@mui/icons-material/Instagram";
import GitHubIcon from "@mui/icons-material/GitHub";

import imageMatias from "../Images/Maty.jpg"; // Tu imagen
import imageJosue from "../Images/Josué.jpg"; // Imagen de Josué

const Footer = () => {
    return (
        <Box sx={{ mt: 5, p: 2, textAlign: "center", backgroundColor: "#f5f5f5", borderTop: "2px solid #ccc" }}>
            {/* Contenedor para los perfiles en una misma fila */}
            <Box sx={{ display: "flex", justifyContent: "center", alignItems: "center", mb: 2 }}>
                {/* Perfil de Matías */}
                <Box sx={{ mx: 3 }}>
                    <IconButton
                        component="a"
                        href="https://www.instagram.com/matiasmontanon/"
                        target="_blank"
                        rel="noopener"
                    >
                        <InstagramIcon fontSize="large" />
                    </IconButton>
                    <IconButton
                        component="a"
                        href="https://github.com/MatiasMontNec"
                        target="_blank"
                        rel="noopener"
                    >
                        <GitHubIcon fontSize="large" />
                    </IconButton>
                    <Avatar
                        alt="Matías Montaño"
                        src={imageMatias}
                        sx={{
                            width: 100,
                            height: 100,
                            margin: "0 auto",
                            mt: 2,
                        }}
                    />
                    <Typography variant="h6" sx={{ mt: 1 }}>
                        Matías Montaño
                    </Typography>
                </Box>

                {/* Perfil de Josué */}
                <Box sx={{ mx: 3 }}>
                    <IconButton
                        component="a"
                        href="https://www.instagram.com/josve.q/"
                        target="_blank"
                        rel="noopener"
                    >
                        <InstagramIcon fontSize="large" />
                    </IconButton>
                    <IconButton
                        component="a"
                        href="https://github.com/JosueQuilenan"
                        target="_blank"
                        rel="noopener"
                    >
                        <GitHubIcon fontSize="large" />
                    </IconButton>
                    <Avatar
                        alt="Josué Quileñan"
                        src={imageJosue}
                        sx={{
                            width: 100,
                            height: 100,
                            margin: "0 auto",
                            mt: 2,
                        }}
                    />
                    <Typography variant="h6" sx={{ mt: 1 }}>
                        Josué Quileñan
                    </Typography>
                </Box>
            </Box>

            {/* Texto sobre las tecnologías */}
            <Typography variant="body1">
                Esta aplicación ha sido desarrollada usando tecnologías como{" "}
                <Link href="https://spring.io/projects/spring-boot" target="_blank" rel="noopener">
                    Spring Boot
                </Link>{" "}
                (para el backend),{" "}
                <Link href="https://reactjs.org/" target="_blank" rel="noopener">
                    React
                </Link>{" "}
                (para el frontend), y{" "}
                <Link href="https://www.pgadmin.org/" target="_blank" rel="noopener">
                    PostgresSQL
                </Link>{" "}
                (para la base de datos).
            </Typography>
        </Box>
    );
};

export default Footer;

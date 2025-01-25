import React, { useState, useEffect } from "react";
import './App.css';
import { Route, Routes, useLocation } from 'react-router-dom';
import Home from "./components/Home.jsx";
import Navbar from "./components/NavBar.jsx";
import NotFound from "./components/NotFound.jsx";
import { ThemeProvider } from "@mui/material";
import { homeTheme, searchTheme, merchandiseTheme, songsTheme, testsTheme, mangasTheme, notFoundTheme } from "./theme";
import Footer from './components/Footer';
import Buscador from "./components/Buscador.jsx";
import Mercancia from "./components/Mercancia.jsx";
import Canciones from "./components/Canciones.jsx";
import Mangas from "./components/Mangas.jsx";
import Tests from "./components/Tests.jsx";
import Lottie from "lottie-react";
import offlineAnimation from "./animations/offline.json";

function App() {
    const location = useLocation();
    const [isOnline, setIsOnline] = useState(navigator.onLine);

    // Escuchar cambios de conexión
    useEffect(() => {
        const handleOnline = () => setIsOnline(true);
        const handleOffline = () => setIsOnline(false);

        window.addEventListener("online", handleOnline);
        window.addEventListener("offline", handleOffline);

        // Limpieza de eventos
        return () => {
            window.removeEventListener("online", handleOnline);
            window.removeEventListener("offline", handleOffline);
        };
    }, []);

    // Determinar el tema según la ruta
    const getTheme = () => {
        switch (location.pathname) {
            case "/buscador":
                return searchTheme;
            case "/mercancia":
                return merchandiseTheme;
            case "/canciones":
                return songsTheme;
            case "/tests":
                return testsTheme;
            case "/mangas":
                return mangasTheme;
            default: // Rutas no encontradas
                return notFoundTheme;
            case "/":
                return homeTheme; // Tema por defecto
        }
    };

    return (
        <ThemeProvider theme={getTheme()}>
            <div className="container">
                {isOnline ? (
                    <>
                        <Navbar />
                        <Routes>
                            <Route path="/" element={<Home />} />
                            <Route path="/buscador" element={<Buscador />} />
                            <Route path="/mercancia" element={<Mercancia />} />
                            <Route path="/canciones" element={<Canciones />} />
                            <Route path="/mangas" element={<Mangas />} />
                            <Route path="/tests" element={<Tests />} />
                            <Route path="*" element={<NotFound />} />
                        </Routes>
                        <Footer />
                    </>
                ) : (
                    <div className="offline-container" style={{ textAlign: "center", marginTop: "50px" }}>
                        <Lottie animationData={offlineAnimation} style={{ width: "300px", margin: "auto" }} />
                        <h2>Sin conexión a Internet</h2>
                        <p>Por favor, verifica tu conexión e inténtalo nuevamente.</p>
                    </div>
                )}
            </div>
        </ThemeProvider>
    );
}

export default App;

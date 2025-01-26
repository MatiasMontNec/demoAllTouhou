import React, { useState, useEffect } from "react";
import './App.css';
import { Route, Routes, useLocation } from 'react-router-dom';
import Home from "./components/Home.jsx";
import Navbar from "./components/NavBar.jsx";
import NotFound from "./components/NotFound.jsx";
import Preguntas from "./components/Preguntas.jsx"; // Importamos el nuevo componente
import { ThemeProvider } from "@mui/material";
import {
    homeTheme,
    searchTheme,
    merchandiseTheme,
    songsTheme,
    testsTheme,
    mangasTheme,
    notFoundTheme,
    pinkTheme // Importamos el nuevo tema
} from "./theme";
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

    useEffect(() => {
        const handleOnline = () => setIsOnline(true);
        const handleOffline = () => setIsOnline(false);

        window.addEventListener("online", handleOnline);
        window.addEventListener("offline", handleOffline);

        return () => {
            window.removeEventListener("online", handleOnline);
            window.removeEventListener("offline", handleOffline);
        };
    }, []);

    const getTheme = () => {
        switch (location.pathname.split("/")[1]) {
            case "buscador":
                return searchTheme;
            case "mercancia":
                return merchandiseTheme;
            case "canciones":
                return songsTheme;
            case "tests":
                return testsTheme;
            case "mangas":
                return mangasTheme;
            case "Preguntas":
                return pinkTheme; // Tema para Preguntas
            default:
                return location.pathname === "/" ? homeTheme : notFoundTheme;
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
                            <Route path="/Preguntas/:id" element={<Preguntas />} />
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

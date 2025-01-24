import './App.css';
import { Route, Routes, useLocation } from 'react-router-dom';
import Home from "./components/Home.jsx";
import Navbar from "./components/NavBar.jsx";
import NotFound from "./components/NotFound.jsx";
import { ThemeProvider } from "@mui/material";
import { homeTheme, searchTheme, merchandiseTheme, songsTheme, testsTheme } from "./theme";
import Footer from './components/Footer'; // Importa el Footer

import Buscador from "./components/Buscador.jsx"; // Importación pendiente
// import Mercancia from "./components/Mercancia.jsx"; // Importación pendiente
// import Canciones from "./components/Canciones.jsx"; // Importación pendiente
// import Tests from "./components/Tests.jsx"; // Importación pendiente

function App() {
    const location = useLocation();

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
            default:
                return homeTheme; // Tema por defecto
        }
    };

    return (
        <ThemeProvider theme={getTheme()}>
            <div className="container">
                <Navbar />
                <Routes>
                    <Route path="/" element={<Home />} />
                    <Route path="/buscador" element={<Buscador />} />
                    {/* <Route path="/mercancia" element={<Mercancia />} /> */}
                    {/* <Route path="/canciones" element={<Canciones />} /> */}
                    {/* <Route path="/tests" element={<Tests />} /> */}
                    <Route path="*" element={<NotFound />} />
                </Routes>
                <Footer /> {/* Agregar Footer al final de la página */}
            </div>
        </ThemeProvider>
    );
}

export default App;

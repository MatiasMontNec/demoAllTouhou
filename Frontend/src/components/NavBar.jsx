import { useState, useEffect } from "react";
import { AppBar, Box, Toolbar, Typography, Button, IconButton, Dialog, DialogActions, DialogContent, DialogTitle } from "@mui/material";
import { useNavigate, useLocation } from "react-router-dom";
import logo from "../assets/Touhou101.png"; // Logo de la aplicación
import ArrowUpwardIcon from '@mui/icons-material/ArrowUpward';
import HelpIcon from '@mui/icons-material/Help'; // Icono de ayuda

export default function Navbar() {
    const navigate = useNavigate();
    const [scrollPosition, setScrollPosition] = useState(0); // Estado para la posición del scroll
    const [openHelpDialog, setOpenHelpDialog] = useState(false); // Estado para abrir/cerrar el diálogo de ayuda
    const location = useLocation();  // Usar useLocation para obtener la ubicación actual
    const [helpClicked, setHelpClicked] = useState(false);  // Estado para el icono de ayuda
    const [currentSection, setCurrentSection] = useState(0);  // Estado para la sección actual

    // Detectar cuando la ruta cambia y reiniciar el estado de la animación
    useEffect(() => {
        setHelpClicked(false); // Reiniciar el estado de la animación cuando cambia la ruta
        setCurrentSection(0); // Reiniciar la sección cuando cambia la ruta
    }, [location]);

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

    // Función para abrir el cuadro de ayuda
    const handleOpenHelp = () => {
        setOpenHelpDialog(true);
        setHelpClicked(true); // Marcar que se ha presionado el botón de ayuda
    };

    // Función para cerrar el cuadro de ayuda
    const handleCloseHelp = () => {
        setOpenHelpDialog(false);
    };

    // Función para obtener las secciones de ayuda, y manejar el cambio según la ruta actual
    const getHelpSections = () => {
        switch (location.pathname.split("/")[1]) {
            case "buscador":
                return [
                    { title: "Buscar Productos", text: "En esta sección, puedes buscar productos específicos utilizando el campo de búsqueda. Escribe lo que estás buscando y haz clic en 'Buscar' para ver los resultados." },
                    { title: "Filtros de Búsqueda", text: "Puedes refinar los resultados de la búsqueda utilizando filtros. Los filtros disponibles incluyen la opción de buscar por 'Personaje' o 'Juego', y puedes agregar criterios adicionales como nombre, edad o fecha de salida." },
                    { title: "Resultados", text: "Después de realizar una búsqueda, los resultados se mostrarán en esta sección. Cada resultado mostrará una imagen, el título y una breve descripción del producto o personaje que has buscado." },
                ];
            case "mercancia":
                return [
                    { title: "Explora la Tienda", text: "Explora nuestra tienda con productos disponibles." },
                    { title: "Buscar Productos", text: "Usa el campo de búsqueda para encontrar productos por nombre." },
                    { title: "Filtros de Búsqueda", text: "Puedes aplicar filtros como precio, fecha y personajes para refinar los resultados." },
                    { title: "Resultados de Búsqueda", text: "Aquí se muestran los productos que coinciden con tu búsqueda y filtros. Haz clic en un producto para ver más detalles." },
                ];
            case "canciones":
                return [
                    { title: "Buscar por Título", text: "Usa el buscador para encontrar productos por título. Simplemente escribe lo que buscas y haz clic en el botón de búsqueda." },
                    { title: "Filtros de Búsqueda", text: "Aplica filtros para refinar los resultados según artista o personaje. Puedes elegir más de un filtro para una búsqueda más específica." },
                    { title: "Filtro Artista", text: "Si seleccionas el filtro 'Artista', podrás buscar productos relacionados con un artista específico." },
                    { title: "Filtro Personaje", text: "Si eliges el filtro 'Personaje', podrás buscar productos relacionados con un personaje específico." },
                    { title: "Filtros Dinámicos", text: "Cuando aplicas un filtro, se te presentarán campos adicionales para refinar la búsqueda según el artista o personaje." },
                    { title: "Resultados de Búsqueda", text: "Los resultados de la búsqueda se mostrarán aquí. Cada producto tendrá su imagen, título y una breve descripción. Haz clic para obtener más detalles." },
                ];
            case "tests":
                return [
                    { title: "Tests", text: "Esta sección muestra 4 Tests donde cada uno contiene una imagen con título y una breve descripción. Al pasar el cursor sobre la imagen, aparecerá más información y un botón para redirigir a otra página." },
                ];

            case "mangas":
                return [
                    { title: "Buscador General", text: "Esta sección permite a los usuarios buscar contenido por título, con la opción de aplicar filtros adicionales para refinar los resultados." },
                    { title: "Búsqueda por Título", text: "El usuario puede escribir un término en el campo de búsqueda y hacer clic en el botón de 'Buscar' para iniciar la búsqueda. Si no se ingresa un término de búsqueda o no se seleccionan filtros, el botón de 'Buscar' estará deshabilitado." },
                    { title: "Filtros por Tipo de Búsqueda", text: "Existen filtros específicos para refinar la búsqueda por tipo de contenido, como por 'Autor' o 'Personaje'. Al seleccionar uno de estos filtros, el campo correspondiente se mostrará para permitir al usuario ingresar más detalles." },
                    { title: "Filtros Dinámicos", text: "Cuando se selecciona un filtro, como 'Autor' o 'Personaje', aparece un campo adicional para ingresar el valor de búsqueda relacionado. Esto permite una búsqueda más precisa." },
                    { title: "Resultados de la Búsqueda", text: "Los resultados de la búsqueda se muestran en una cuadrícula. Si no se encuentran resultados, se mostrará un mensaje indicándolo. Si la búsqueda está en progreso, se mostrará una animación de carga." },
                    { title: "Resultados Detallados", text: "Cada resultado se presenta con su imagen, título y una breve descripción. Si el resultado tiene más detalles, estos se pueden visualizar haciendo clic en el elemento." },
                ];
            case "Preguntas":
                return [
                    { title: "Pantalla de Encuesta", text: "Esta pantalla presenta una encuesta interactiva con una serie de preguntas, una barra de progreso que muestra el avance y varios botones para navegar entre las preguntas." },
                    { title: "Barra de Progreso", text: "Una barra de progreso visualiza el porcentaje de avance en la encuesta, que se actualiza dinámicamente." },
                    { title: "Preguntas de la Encuesta", text: "La encuesta presenta hasta tres preguntas a la vez. Cada pregunta se muestra con una escala de respuesta, donde puedes hacer clic en círculos para indicar su nivel de acuerdo con la afirmación presentada." },
                    { title: "Navegación entre Preguntas", text: "Los botones 'Siguiente' y 'Regresar' permiten al usuario navegar entre las preguntas. El botón 'Siguiente' se habilita solo si todas las preguntas actuales han sido respondidas." },
                    { title: "Finalización de la Encuesta", text: "Al finalizar la encuesta, un botón 'Ver mis resultados' aparece. Este botón solo se habilita cuando todas las preguntas han sido respondidas, y al hacer clic en él, puede ver los resultados finales." },
                ];

            case "":
                return [
                    { title: "Bienvenido a la Página Principal", text: "Le presentamos lo que contiene nuestra aplicación web a través de imagenes interactivas y botones." },
                    { title: "Sección informativa", text: "Le damos contexto acerca de que es touhou project y nosotros." },
                ];
            default:
                return [
                    { title: "Página no encontrada", text: "La ruta que buscas no existe. Verifica la URL o vuelve a la página de inicio." },
                ];
        }
    };

    // Función para ir a la siguiente sección
    const handleNext = () => {
        if (currentSection < getHelpSections().length - 1) {
            setCurrentSection(currentSection + 1);
        }
    };

    // Función para ir a la sección anterior
    const handlePrevious = () => {
        if (currentSection > 0) {
            setCurrentSection(currentSection - 1);
        }
    };

    const currentHelpSection = getHelpSections()[currentSection];

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
                            onClick={() => handleNavigation("/mangas")}
                            sx={{
                                marginRight: 2,
                                "&:hover": {
                                    backgroundColor: "rgba(255, 255, 255, 0.2)",
                                },
                            }}
                        >
                            Ver Mangas
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

            {/* Icono de ayuda fuera de la barra */}
            <Box sx={{
                position: 'fixed',
                top: 20,
                right: 20,
                zIndex: 9999, // Asegura que esté por encima de otros elementos
                animation: helpClicked ? 'none' : 'waveAnimation 1s infinite', // Animación solo si no se ha presionado
            }}>
                <IconButton
                    color="primary"
                    onClick={handleOpenHelp}
                    sx={{
                        fontSize: 70, // Tamaño más grande del icono
                        '&:hover': {
                            backgroundColor: 'rgba(255, 255, 255, 0.3)',
                        },
                    }}
                >
                    <HelpIcon sx={{ fontSize: 'inherit' }} />
                </IconButton>
            </Box>

            {/* Cuadro de diálogo de ayuda */}
            <Dialog open={openHelpDialog} onClose={handleCloseHelp}>
                <DialogContent>
                    <Typography variant="h5">{currentHelpSection.title}</Typography>
                    <Typography variant="h6">{currentHelpSection.text}</Typography>
                </DialogContent>
                <DialogActions sx={{ display: 'block' }}>
                    {/* Fila para los botones "Anterior" y "Siguiente" */}
                    <Box sx={{ display: 'flex', justifyContent: 'right', width: '100%' }}>
                        <Button onClick={handlePrevious} disabled={currentSection === 0} color="primary">
                            Anterior
                        </Button>
                        <Button onClick={handleNext} disabled={currentSection === getHelpSections().length - 1} color="primary">
                            Siguiente
                        </Button>
                    </Box>
                    {/* Fila para el botón "Cerrar" */}
                    <Box sx={{ display: 'flex', justifyContent: 'right', marginTop: 2 }}>
                        <Button
                            onClick={handleCloseHelp}
                            color="primary"
                            sx={{
                                fontWeight: 'bold',  // Hacer el texto más grueso
                                padding: '8px 16px', // Espaciado más grande
                            }}
                        >
                            Cerrar
                        </Button>
                    </Box>

                </DialogActions>
            </Dialog>

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

            {/* Animación CSS para las ondas */}
            <style>{`
                @keyframes waveAnimation {
                    0% {
                        transform: scale(1);
                        opacity: 1;
                    }
                    50% {
                        transform: scale(1.5);
                        opacity: 0.7;
                    }
                    100% {
                        transform: scale(1);
                        opacity: 1;
                    }
                }
            `}</style>
        </Box>
    );
}

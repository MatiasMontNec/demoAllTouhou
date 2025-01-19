import { useState } from "react";
import { AppBar, Box, IconButton, Toolbar, Typography, Dialog, DialogTitle, DialogContent, DialogActions, Button, Popover, Fade } from "@mui/material";
import HelpOutlineIcon from "@mui/icons-material/HelpOutline";
import MenuIcon from '@mui/icons-material/Menu';
import { useLocation } from "react-router-dom";
import KeyboardArrowDownIcon from "@mui/icons-material/KeyboardArrowDown";
import Sidemenu from "./SideMenu.jsx";

export default function Navbar() {
    const [open, setOpen] = useState(false);
    const [helpOpen, setHelpOpen] = useState(false);
    const [popoverAnchor, setPopoverAnchor] = useState(null);
    const [showScrollArrow, setShowScrollArrow] = useState(false);
    const [popoverShown, setPopoverShown] = useState(false);
    const location = useLocation();

    const toggleHelpDialog = () => {
        setHelpOpen(!helpOpen); // Abre o cierra el Dialog
    };

    const toggleDrawer = (open) => (event) => {
        setOpen(open);
    };

    useState(() => {
        const handleScroll = () => {
            const scrollTop = window.scrollY;
            const windowHeight = window.innerHeight;
            const documentHeight = document.documentElement.scrollHeight;

            if (scrollTop + windowHeight >= documentHeight) {
                setShowScrollArrow(false);
            } else {
                setShowScrollArrow(true);
            }
        };

        window.addEventListener("scroll", handleScroll);
        return () => window.removeEventListener("scroll", handleScroll);
    }, [location.pathname]);

    useState(() => {
        const interval = setInterval(() => {
            setPopoverAnchor(document.body);
            setPopoverShown(true);
        }, 60000); // Cambia a 60 segundos
        return () => clearInterval(interval);
    }, []);

    return (
        <Box sx={{ flexGrow: 1 }}>
            <AppBar position="static">
                <Toolbar>
                    <IconButton
                        size="large"
                        edge="start"
                        color="inherit"
                        aria-label="menu"
                        sx={{ mr: 2 }}
                        onClick={toggleDrawer(true)}
                    >
                        <MenuIcon />
                    </IconButton>

                    <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
                        Touhou101
                    </Typography>

                    <IconButton color="inherit" onClick={toggleHelpDialog}>
                        <HelpOutlineIcon />
                    </IconButton>
                </Toolbar>
            </AppBar>

            <Sidemenu open={open} toggleDrawer={toggleDrawer} />

            {/* Dialog para mostrar ayuda */}
            <Dialog open={helpOpen} onClose={toggleHelpDialog} fullWidth maxWidth="sm">
                <DialogTitle>Ayuda</DialogTitle>
                <DialogContent>
                    <Typography variant="body1">Aquí va el texto de ayuda...</Typography>
                </DialogContent>
                <DialogActions>
                    <Button onClick={toggleHelpDialog} color="primary">
                        Cerrar
                    </Button>
                </DialogActions>
            </Dialog>

            {/* Popover */}
            <Popover
                open={Boolean(popoverAnchor)}
                anchorEl={popoverAnchor}
                onClose={() => setPopoverAnchor(null)}
                anchorOrigin={{
                    vertical: "top",
                    horizontal: "right",
                }}
            >
                <Box p={2}>
                    <Typography variant="body1" sx={{ fontWeight: "bold" }}>
                        ¿Te quedaste atascado? Consulta el botón de ayuda.
                        <br />
                        Está en la esquina derecha de la barra azul.
                        <br />
                        Presiona el símbolo de interrogación para obtener la ayuda.
                    </Typography>
                    <Button
                        variant="contained"
                        color="inherit"
                        onClick={() => setPopoverAnchor(null)}
                        sx={{
                            marginTop: 1,
                        }}
                    >
                        Cerrar
                    </Button>
                </Box>
            </Popover>

            <Fade in={showScrollArrow}>
                <Box
                    sx={{
                        position: "fixed",
                        right: 16,
                        bottom: 32,
                        zIndex: 1000,
                        backgroundColor: "rgba(0, 0, 0, 0.6)",
                        borderRadius: "50%",
                        padding: "8px",
                        cursor: "pointer",
                    }}
                    onClick={() => {
                        window.scrollBy({ top: window.innerHeight / 2, behavior: "smooth" });
                    }}
                >
                    <KeyboardArrowDownIcon sx={{ color: "white", fontSize: "2rem" }} />
                </Box>
            </Fade>
        </Box>
    );
}

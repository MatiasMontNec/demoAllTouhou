import React, { useState, useEffect } from "react";
import { Box, Typography, IconButton, Slider } from "@mui/material";
import ArrowBackIosIcon from "@mui/icons-material/ArrowBackIos";
import ArrowForwardIosIcon from "@mui/icons-material/ArrowForwardIos";
import { Grid} from "@mui/material";
import { MusicNote, AccountCircle, ArtTrack, Group } from "@mui/icons-material";

// Importa las imágenes locales
import image1 from "../Images/Reimu.jpg";
import image4 from "../Images/MEME.jpg";
import image3 from "../Images/Scarlets.jpg";
import image2 from "../Images/Yuuka.jpg";
import image5 from "../Images/buscar.jpg";
import image6 from "../Images/mercancía.jpg";
import image7 from "../Images/canciones2.jpg";
import image8 from "../Images/test2.jpg";
import image9 from "../Images/Touhou.jpg";
import image10 from "../Images/Touhou101.jpg";
import image11 from "../Images/wallpaper.jpg";

const imageTexts = [
    "Busca acerca de Touhou Project!",
    "Compra acerca de Touhou Project!",
    "Descubre canciones de Touhou Project!",
    "Prueba divertidos Tests de Touhou Project!",
];

const links = [
    "/buscador", // Ruta asociada a la imagen 1
    "/mercancia", // Ruta asociada a la imagen 2
    "/canciones", // Ruta asociada a la imagen 3
    "/tests",     // Ruta asociada a la imagen 4
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
            <Box
                sx={{ position: "relative", overflow: "hidden", width: "100%", height: "400px", cursor: "pointer" }}
                onClick={() => window.location.href = links[currentImage]} // Redirigir al enlace correspondiente
            >
                {/* Imagen actual */}
                <img
                    src={images[currentImage]}
                    alt={`Imagen ${currentImage + 1}`}
                    style={{
                        width: "100%",
                        height: "100%",
                        objectFit: "cover",
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

            {/* Cuadros con imágenes y enlaces */}
            <Box sx={{ display: "flex", justifyContent: "space-between", flexWrap: "wrap", gap: 2, mt: 4 }}>
                {[
                    { image: image5, link: "/buscador" },
                    { image: image6, link: "/mercancia" },
                    { image: image7, link: "/canciones" },
                    { image: image8, link: "/tests" },
                ].map((item, index) => (
                    <Box
                        key={index}
                        onClick={() => (window.location.href = item.link)} // Redirigir
                        sx={{
                            width: 220, // Aumentado el tamaño
                            height: 220, // Aumentado el tamaño
                            overflow: "hidden",
                            borderRadius: 10,
                            cursor: "pointer",
                            position: "relative",
                            transition: "box-shadow 0.3s ease",
                            "&:hover": {
                                boxShadow: "0 4px 15px rgba(0, 0, 0, 0.3)",
                            },
                            "&:hover img": {
                                transform: "scale(1.2)",
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


            {/* Primera fila informativa */}
            <Box
                sx={{
                    display: "flex",
                    justifyContent: "space-between",
                    alignItems: "center",
                    mt: 5,
                    gap: 4,
                    backgroundColor: "#cae5e8", // Fondo similar a paper
                    borderRadius: 3,
                    padding: "20px", // Espaciado interno
                }}
            >
                {/* Contenido existente */}
                <Box sx={{ flex: 1 }}>
                    <Typography variant="h4" sx={{ mb: 2, fontWeight: "bold", textAlign: "center" }}>
                        ¿Qué es Touhou Project?
                    </Typography>
                    <Typography variant="body1" sx={{ color: "text.secondary", mb: 1, textAlign: "justify", fontSize: "1.2rem" }}>
                        Touhou Project es mucho más que una serie de videojuegos Bullet Hell. Aunque su premisa principal parece sencilla —esquivar una lluvia de balas mientras derrotas enemigos—, lo que realmente lo hace único es la pasión y creatividad que lo rodean.
                    </Typography>
                    <Typography variant="body1" sx={{ color: "text.secondary", mb: 1, textAlign: "justify", fontSize: "1.2rem" }}>
                        Creado por ZUN, el apodo del diseñador y alma detrás del proyecto, junto a su equipo Team Shanghai Alice, Touhou es el resultado de un sueño: hacer juegos que lo satisficieran a él mismo. Esa libertad creativa trajo al mundo algo mágico.
                    </Typography>
                    <Typography variant="body1" sx={{ color: "text.secondary", mt: 2, textAlign: "justify", fontSize: "1.2rem" }}>
                        Touhou Project no es solo un videojuego; es un universo lleno de vida, creatividad y conexión. Es el testimonio de cómo una pasión personal puede convertirse en algo que millones de personas en el mundo aman y celebran.
                    </Typography>
                </Box>

                <Box sx={{ flex: 1, display: "flex", justifyContent: "center" }}>
                    <img
                        src={image9}
                        alt="Touhou Project"
                        style={{
                            width: "80%",
                            borderRadius: 10,
                            objectFit: "cover",
                        }}
                    />
                </Box>
            </Box>

            {/* Cuadros con iconos, títulos y textos */}
            <Grid container spacing={4} sx={{ mt: 4 }}>
                {/* Fila 1 */}
                <Grid item xs={12} sm={6} md={6}>
                    <Box sx={{ display: "flex", flexDirection: "column", alignItems: "center" }}>
                        <MusicNote sx={{ fontSize: 40, color: "primary.main", mb: 2 }} />
                        <Typography variant="h6" sx={{ mb: 1 }}>
                            El Legado Musical
                        </Typography>
                        <Typography variant="body1" sx={{ color: "text.secondary", textAlign: "justify", fontSize: "1.2rem" }}>
                            El soundtrack de Touhou es legendario. Las composiciones de ZUN han servido de inspiración
                            para innumerables obras de fans: remixes, canciones originales, colaboraciones e incluso
                            memes. De hecho, hay personas que reconocen la música de Touhou sin siquiera saber de dónde
                            proviene. Su impacto es tan profundo que muchas melodías se han vuelto un emblema dentro y
                            fuera del fandom, creando una conexión única con la comunidad.
                        </Typography>
                    </Box>
                </Grid>

                <Grid item xs={12} sm={6} md={6}>
                    <Box sx={{ display: "flex", flexDirection: "column", alignItems: "center" }}>
                        <AccountCircle sx={{ fontSize: 40, color: "primary.main", mb: 2 }} />
                        <Typography variant="h6" sx={{ mb: 1 }}>
                            Las Historias y el Universo Expandido
                        </Typography>
                        <Typography variant="body1" sx={{ color: "text.secondary", textAlign: "justify", fontSize: "1.2rem" }}>
                            Touhou destaca por su elenco de personajes fascinantes, cada uno con trasfondos basados en
                            mitología japonesa, cuentos populares, vampiros, religiones y más. Aunque ZUN ofrece
                            detalles mínimos en los juegos, este "vacío" se convirtió en una invitación para los fans, quienes
                            han expandido el universo de Touhou con teorías, historias propias y una creatividad sin límites.
                            El resultado: un mundo vivo que crece más allá de lo que ZUN imaginó.
                        </Typography>
                    </Box>
                </Grid>

                {/* Fila 2 */}
                <Grid item xs={12} sm={6} md={6}>
                    <Box sx={{ display: "flex", flexDirection: "column", alignItems: "center" }}>
                        <ArtTrack sx={{ fontSize: 40, color: "primary.main", mb: 2 }} />
                        <Typography variant="h6" sx={{ mb: 1 }}>
                            Diseño Único de Personajes
                        </Typography>
                        <Typography variant="body1" sx={{ color: "text.secondary", textAlign: "justify", fontSize: "1.2rem" }}>
                            Si bien los dibujos de ZUN no son considerados obras de arte tradicionales -de hecho, algunos
                            diseños como el de Sakuya Izayoi en Touhou 6 han sido objeto de bromas—, su estilo se ha vuelto
                            icónico. El llamado "ZUN Art" es tan único que se ha convertido en un símbolo de la serie, y muchos
                            artistas lo recrean como un tributo. La magia de sus personajes no solo radica en los diseños, sino
                            en su origen: ZUN se inspira únicamente en lo que le gusta, desde mitos hasta leyendas, y esa
                            autenticidad resuena profundamente en su comunidad.
                        </Typography>
                    </Box>
                </Grid>

                <Grid item xs={12} sm={6} md={6}>
                    <Box sx={{ display: "flex", flexDirection: "column", alignItems: "center" }}>
                        <Group sx={{ fontSize: 40, color: "primary.main", mb: 2 }} />
                        <Typography variant="h6" sx={{ mb: 1 }}>
                            La Comunidad y el Fandom
                        </Typography>
                        <Typography variant="body1" sx={{ color: "text.secondary", textAlign: "justify", fontSize: "1.2rem" }}>
                            Lo más sorprendente de Touhou es la relación entre ZUN y su fandom. Él nunca ha puesto
                            restricciones para que los fans creen contenido basado en su obra. Esto desató una explosión de
                            creatividad que ha llevado a Touhou a trascender fronteras, con productos, eventos, e incluso
                            reconocimiento internacional. ZUN, a su vez, adora a su comunidad y participa activamente en
                            eventos, fortaleciendo aún más este vínculo especial.
                        </Typography>
                    </Box>
                </Grid>
            </Grid>


            {/* Segunda fila informativa */}
            <Box
                sx={{
                    display: "flex",
                    justifyContent: "space-between",
                    alignItems: "center",
                    mt: 5,
                    gap: 4,
                    backgroundColor: "#cae5e8", // Fondo similar a paper
                    borderRadius: 3,
                    padding: "20px", // Espaciado interno
                }}
            >
                {/* Contenido existente */}
                <Box sx={{ flex: 1, display: "flex", justifyContent: "center" }}>
                    <img
                        src={image10}
                        alt="Touhou101"
                        style={{
                            width: "80%",
                            borderRadius: 10,
                            objectFit: "cover",
                        }}
                    />
                </Box>

                <Box sx={{ flex: 1 }}>
                    <Typography variant="h4" sx={{ mb: 2, fontWeight: "bold", textAlign: "center" }}>
                        ¿Qué es Touhou101?
                    </Typography>
                    <Typography variant="body1" sx={{ color: "text.secondary", mb: 1, textAlign: "justify", fontSize: "1.2rem" }}>
                        Touhou101 nació con el propósito de ser un punto de encuentro para fanáticos de Touhou Project,
                        un lugar donde puedas descubrir, aprender y conectar con otros apasionados por este increíble universo.
                    </Typography>
                    <Typography variant="body1" sx={{ color: "text.secondary", mb: 1, textAlign: "justify", fontSize: "1.2rem" }}>
                        Nuestro equipo está formado por entusiastas que aman el mundo de Gensokyo y todo lo que Touhou Project representa.
                        Trabajamos para traerte contenido accesible, informativo y divertido que celebre la creatividad y comunidad que este proyecto inspira.
                    </Typography>
                    <Typography variant="body1" sx={{ color: "text.secondary", mt: 2, textAlign: "justify", fontSize: "1.2rem" }}>
                        En Touhou101 encontrarás recursos para explorar la música, los personajes, el lore, y mucho más. Queremos que esta página
                        sea una ventana para introducir nuevos fanáticos al universo Touhou y un espacio para fortalecer a nuestra increíble comunidad.
                    </Typography>
                </Box>
            </Box>
            <br/>
            <img
                src={image11} // Sustituye con la ruta de tu imagen
                alt="Imagen Final"
                style={{
                    width: "100%", // Ocupa el 100% del contenedor
                    maxWidth: "1500px", // Limita el tamaño máximo de la imagen
                    borderRadius: "10px", // Esquinas redondeadas
                    objectFit: "cover", // Ajusta la imagen para cubrir el espacio sin deformarse
                }}
            />
        </Box>
    );
};

export default Home;

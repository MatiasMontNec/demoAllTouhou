import React, {useEffect, useState} from "react";
import { useParams } from "react-router-dom";
import { Box, Typography, LinearProgress, Button } from "@mui/material";
import { createTheme } from "@mui/material/styles";
import testCharacter from "../Images/Character.jpg";
import testLike from "../Images/Like.jpg";
import testHate from "../Images/Hate.jpg";
import testPlace from "../Images/Place.jpg";
import { saveToSessionStorage, loadFromSessionStorage } from "../services/sessionStorageServiceId";

const pinkTheme = createTheme({
    palette: {
        primary: { main: "#E91E63" }, // Rosa fuerte
        secondary: { main: "#F06292" }, // Rosa claro
        background: { default: "#FCE4EC", paper: "#F8BBD0" }, // Fondo rosado suave
        text: { primary: "#880E4F", secondary: "#AD1457" }, // Texto rosado oscuro
    },
});

const Preguntas = () => {
    const { id } = useParams(); // Obtener el id de la URL

    // Datos del test
    const testData = {
        1: {
            title: "¿Qué personaje de Touhou eres?",
            description:
                "Descubre qué personaje de Touhou representa mejor tu personalidad. Este test se demorará en menos de 5 minutos.",
            questions: [
                "¿Te consideras una persona más joven que el promedio de tus amigos?",
                "¿Prefieres mantener una actitud calmada, sin importar la situación?",
                "¿Te gustaría tener la habilidad de volar o flotar libremente?",
                "¿Te consideras más alto que la mayoría de las personas que conoces?",
                "¿Prefieres estar rodeado de personas que tengan una energía calmada y serena?",
                "¿Sientes que tus emociones son intensas y difíciles de controlar?",
                "¿Crees que tus habilidades físicas son tu mayor fortaleza?",
                "¿Disfrutas siendo el centro de atención en una reunión o evento?",
                "¿Te atraen más los seres de gran tamaño o imponentes?",
                "¿Prefieres pasar tiempo en la naturaleza en lugar de en entornos urbanos?",
                "¿Te interesa ser parte de un grupo que tiene poder o influencia sobre otros?",
                "¿Te sientes más cómodo en lugares altos, como en montañas o azoteas?",
                "¿Te consideras una persona que, a menudo, prefiere actuar en solitario?",
                "¿Te atraen las personas que tienen una personalidad fuerte y dominante?",
                "¿Te consideras alguien con una gran capacidad para adaptarte a cualquier entorno?",
                "¿Te gustaría tener poderes sobrenaturales o mágicos?",
                "¿Prefieres estar rodeado de personas que te ayuden a mantener el orden?",
                "¿Te gustaría tener un poder que te permita controlar la mente de otros?",
                "¿Te atraen las personas que son misteriosas y mantienen secretos?",
                "¿Te consideras alguien con una gran resistencia a los desafíos?",
                "¿Te gusta tener una personalidad tranquila y serena frente a la adversidad?",
                "¿Prefieres enfrentarte a los problemas con fuerza y determinación?",
                "¿Te consideras más bajo de estatura en comparación con la mayoría de las personas?",
                "¿Te atraen los personajes que tienen una presencia imponente o intimidante?",
                "¿Te sientes más a gusto en grupos pequeños o con personas de confianza?",
                "¿Te gustaría ser un líder en lugar de seguir a los demás?",
                "¿Te atraen más los personajes que prefieren trabajar en equipo en lugar de hacerlo solo?",
                "¿Te interesan las personas que tienen habilidades de lucha o combate?",
                "¿Te gustaría tener un poder que te permita manipular el tiempo o el espacio?",
                "¿Prefieres a las personas que disfrutan de aventuras y desafíos?"
            ]
        },
        2: {
            title: "¿Qué personaje de Touhou te amaria?",
            description:
                "Descubre qué personaje de Touhou te gustaría tener como novia o esposa!. Este test se demorará en menos de 5 minutos.",
            questions: [
                "¿Te consideras una persona que prefiere la compañía de personas mayores que de personas más jóvenes?",
                "¿Sientes que te gustaría estar en lugares altos o elevados?",
                "¿Prefieres a las personas que tienen una personalidad enérgica?",
                "¿Te sientes atraído por personajes que son conocidos por su sabiduría?",
                "¿Te gustaría compartir tus pensamientos en un grupo grande de personas?",
                "¿Te gustan las personas que tienen un físico imponente?",
                "¿Preferirías estar rodeado de gente que es tranquila y silenciosa?",
                "¿Te gustan las personas que tienen poderes especiales o habilidades únicas?",
                "¿Te atraen las personas que suelen estar preocupadas por el bienestar de los demás?",
                "¿Te interesa la magia y lo sobrenatural?",
                "¿Prefieres estar en un lugar cálido en lugar de en uno frío?",
                "¿Te atraen las personas con una fuerte conexión emocional con la naturaleza?",
                "¿Te gustan los personajes que son muy protectores con los demás?",
                "¿Te sentirías atraído por alguien que tiene una personalidad impredecible?",
                "¿Te gustan las personas que son un poco introvertidas?",
                "¿Te atraen las personas que muestran fuerza física y resistencia?",
                "¿Preferirías estar cerca de alguien que es muy sensible a las emociones de otros?",
                "¿Te atraen las personas que tienen una postura firme sobre lo que creen?",
                "¿Te gustan las personas que suelen tomar la iniciativa en los grupos?",
                "¿Te sentirías atraído por alguien que tiene una postura intelectual y filosófica?",
                "¿Te atraen las personas con habilidades para el combate?",
                "¿Prefieres a personas que son más racionales que emocionales?",
                "¿Te atraen las personas que siempre están listas para la aventura?",
                "¿Te interesan más las personas que actúan sin pensar demasiado en las consecuencias?",
                "¿Te gustan las personas que son tranquilas y compuestas bajo presión?",
                "¿Preferirías estar cerca de alguien que se siente atraído por el misterio?",
                "¿Te gustan las personas que tienen una fuerte moral y ética?",
                "¿Te atraen las personas que tienen una gran conexión con los animales?",
                "¿Prefieres a personas que disfrutan de las sorpresas y lo inesperado?",
                "¿Te atraen las personas que son conocidas por su astucia y estrategia?"
            ]
        },
        3: {
            title: "¿Qué personaje de Touhou te odiaría?",
            description:
                "Descubre qué personaje de Touhou podría no llevarse bien contigo. Este test se demorará en menos de 5 minutos.",
            questions: [
                "¿Tienes una tendencia a desafiar las reglas sin pensarlo dos veces?",
                "¿Te resulta fácil hacer enemigos cuando no estás de acuerdo con alguien?",
                "¿Te consideras alguien que se siente incómodo con las reglas y la estructura?",
                "¿Crees que las personas que son demasiado tranquilas te resultan aburridas?",
                "¿Tienes una gran necesidad de destacar entre los demás, incluso a costa de los demás?",
                "¿Prefieres una vida solitaria sin la intervención de otras personas?",
                "¿Te irrita que las personas sigan las normas sin cuestionarlas?",
                "¿Sientes que las personas con autoridad siempre están equivocadas?",
                "¿Te molesta cuando las personas no están dispuestas a pelear por lo que quieren?",
                "¿Te resulta difícil tener paciencia con las personas que no toman decisiones rápidas?",
                "¿Te gustaría que las personas dejaran de seguir la corriente y fueran más audaces?",
                "¿A menudo sientes que los demás te están controlando o limitando?",
                "¿Te consideras alguien que prefiere actuar en solitario sin la ayuda de los demás?",
                "¿Te disgusta tener que lidiar con personas muy emocionales o sensibles?",
                "¿Sientes que las personas que son demasiado optimistas te parecen ingenuas?",
                "¿Te cuesta aceptar las ideas de otros cuando no estás de acuerdo con ellas?",
                "¿Crees que las personas que se dejan influenciar fácilmente son irritantes?",
                "¿Te consideras una persona que prefiere imponer su voluntad sobre otros?",
                "¿Te molesta cuando las personas no defienden lo que creen?",
                "¿Prefieres actuar con violencia o agresividad cuando las cosas no salen como esperabas?",
                "¿Te incomodan las personas que prefieren la paz en lugar de un buen conflicto?",
                "¿A menudo te sientes superior a los demás por tus opiniones y habilidades?",
                "¿Te parece molesto que las personas se conformen con lo que tienen sin aspirar a más?",
                "¿Tiendes a rechazar las ideas que provienen de alguien que consideras débil?",
                "¿Te sientes irritado cuando alguien te desafía de manera directa?",
                "¿Consideras que las personas que intentan evitar el conflicto son débiles?",
                "¿Te molesta la idea de tener que seguir las normas que no has creado tú mismo?",
                "¿Te parece insuficiente la idea de trabajar en grupo cuando prefieres hacer todo por ti mismo?",
                "¿Te desagrada la idea de ser comprendido o apreciado por otros?"
            ]
        },
        4: {
            title: "¿Qué lugar de Touhou vivirías?",
            description:
                "Descubre el lugar de Touhou que más se adapta a ti. Este test se demorará en menos de 5 minutos.",
            questions: [
                "¿Te atrae la idea de vivir en un lugar donde la magia y los poderes sobrenaturales sean comunes?",
                "¿Preferirías vivir en una zona tranquila y aislada, lejos de la civilización?",
                "¿Te gustaría vivir en un lugar con una población diversa de criaturas y seres sobrenaturales?",
                "¿Disfrutarías de un ambiente donde las reglas sociales no sean muy estrictas?",
                "¿Te gustaría vivir en un lugar donde los conflictos son constantes, pero emocionantes?",
                "¿Preferirías vivir en un lugar donde el clima es variable y cambia constantemente?",
                "¿Te atrae la idea de vivir en un lugar que esté en constante cambio, donde los eventos inesperados son frecuentes?",
                "¿Te gustaría vivir en un lugar que tenga una gran cantidad de vegetación y naturaleza?",
                "¿Te sentirías cómodo en un lugar donde las personas son muy directas y no se andan con rodeos?",
                "¿Preferirías vivir en un lugar donde la edad de los habitantes varíe mucho, desde los más jóvenes hasta los más viejos?",
                "¿Te atrae la idea de vivir en una ciudad subterránea o en un lugar donde pocos seres humanos hayan estado?",
                "¿Preferirías vivir en una zona que sea conocida por su imponente belleza natural y su aislamiento?",
                "¿Te gustaría vivir en un lugar donde las personas o seres sobrenaturales puedan cambiar su forma o habilidades con facilidad?",
                "¿Te interesaría vivir en una zona donde se valora el trabajo en equipo y la cooperación más que la competencia?",
                "¿Te sentirías cómodo en un lugar con habitantes que tienen poderes especiales o habilidades que van más allá de lo humano?",
                "¿Preferirías vivir en un lugar que está rodeado por montañas y naturaleza salvaje?",
                "¿Te gustaría vivir en una región donde la tecnología no tiene tanto impacto y la magia y las habilidades naturales dominan?",
                "¿Te atrae la idea de vivir en un lugar donde las estaciones del año cambian de forma drástica?",
                "¿Preferirías vivir en un lugar donde las reglas son estrictas, pero la organización es eficiente?",
                "¿Te gustaría vivir en un lugar donde la comida es un tema importante y te encuentras rodeado de deliciosas opciones?",
                "¿Te atrae la idea de vivir en un lugar donde los eventos se desarrollan rápidamente y todo cambia constantemente?",
                "¿Te gustaría vivir en un lugar con muchas tradiciones antiguas y una fuerte conexión con el pasado?",
                "¿Preferirías vivir en un lugar donde el poder y la fuerza física se valoran más que la sabiduría o el intelecto?",
                "¿Te atrae la idea de vivir en un lugar con una atmósfera oscura o misteriosa?",
                "¿Preferirías vivir en una ciudad flotante o en un lugar que está suspendido en el aire?",
                "¿Te sentirías más cómodo en un lugar donde la gente se ve a sí misma como parte de una comunidad más grande?",
                "¿Te gustaría vivir en una zona donde los elementos de la naturaleza, como el viento, la lluvia y la nieve, tienen un poder importante?",
                "¿Te atrae la idea de vivir en un lugar donde los peligros están presentes, pero son parte de la vida cotidiana?",
                "¿Preferirías vivir en una zona que es conocida por su música, arte y creatividad?",
                "¿Te gustaría vivir en un lugar donde la naturaleza y los animales son considerados sagrados y tienen un papel importante?",
                "¿Te atrae la idea de vivir en un lugar donde los recursos son limitados y se deben gestionar cuidadosamente?",
                "¿Te gustaría vivir en un lugar que esté lleno de misterios y leyendas por resolver?"
            ]
        }
    };

    const backgroundImages = {
        1: testCharacter,
        2: testLike,
        3: testHate,
        4: testPlace,
    };

    const backgroundImage = backgroundImages[id] || ""; // Imagen por defecto si el id no existe
    const data = testData[id] || {}; // Obtener datos correspondientes al id

    const [progress, setProgress] = useState(() => loadFromSessionStorage(id, "testProgress", 0));
    const [selectedCircles, setSelectedCircles] = useState(() => loadFromSessionStorage(id, "testSelectedCircles", {}));
    const [currentQuestionIndex, setCurrentQuestionIndex] = useState(() =>
        loadFromSessionStorage(id, "currentQuestionIndex", 0)
    );

    // Guardar progreso, respuestas y posición actual en sessionStorage cuando cambien
    useEffect(() => {
        saveToSessionStorage(id, "testProgress", progress);
    }, [progress, id]);

    useEffect(() => {
        saveToSessionStorage(id, "testSelectedCircles", selectedCircles);
    }, [selectedCircles, id]);

    useEffect(() => {
        saveToSessionStorage(id, "currentQuestionIndex", currentQuestionIndex);
    }, [currentQuestionIndex, id]);

    // Función para simular el aumento del progreso
    const handleCircleClick = (questionIndex, value) => {
        // Actualiza el estado de las respuestas seleccionadas
        setSelectedCircles((prev) => {
            const newState = { ...prev, [questionIndex]: value };

            // Calcula el progreso después de actualizar el estado
            const totalAnswered = Object.keys(newState).length; // número de preguntas respondidas
            const newProgress = Math.floor((totalAnswered / data.questions.length) * 100); // porcentaje de progreso redondeado a la parte entera
            setProgress(newProgress); // actualiza el progreso

            return newState;
        });
    };

    const handleNext = () => {
        if (currentQuestionIndex < data.questions.length - 3) {
            setCurrentQuestionIndex(currentQuestionIndex + 3);
        }
    };

    const handleBack = () => {
        if (currentQuestionIndex > 0) {
            setCurrentQuestionIndex(currentQuestionIndex - 3);
        }
    };

    const handleFinish = () => {
        // Aquí iría la lógica para mostrar los resultados
        console.log("Test completado");
    };

    const areAllQuestionsAnswered = () => {
        // Comprobar si las tres preguntas actuales han sido respondidas
        for (let i = 0; i < 3; i++) {
            const questionIndex = currentQuestionIndex + i;
            if (questionIndex < data.questions.length && selectedCircles[questionIndex] === undefined) {
                return false; // Si alguna pregunta no está respondida, deshabilita el botón
            }
        }
        return true; // Si todas las preguntas están respondidas, habilita el botón
    };

    const areAllQuestionsAnswered2 = () => {
        // Comprobar si todas las preguntas han sido respondidas
        for (let i = 0; i < data.questions.length; i++) {
            // Verifica si la respuesta para esa pregunta está en selectedCircles
            if (selectedCircles[i] === undefined) {
                return false; // Si alguna pregunta no está respondida, deshabilita el botón
            }
        }
        return true; // Si todas las preguntas están respondidas, habilita el botón
    };

    return (
        <Box
            sx={{
                width: "100%",
                height: "100vh",
                backgroundImage: `url(${backgroundImage})`,
                backgroundSize: "cover",
                backgroundPosition: "center",
                paddingTop: "50px",
                position: "relative",
                "&::before": {
                    content: '""',
                    position: "absolute",
                    top: 0,
                    left: 0,
                    width: "100%",
                    height: "100%",
                    backgroundColor: "rgba(0, 0, 0, 0.5)",
                    zIndex: 1,
                },
            }}
        >
            <Box
                sx={{
                    position: "absolute",
                    top: "50%",
                    left: "50%",
                    transform: "translate(-50%, -50%)",
                    color: "white",
                    textAlign: "center",
                    zIndex: 2,
                }}
            >
                <Typography variant="h4" sx={{ marginBottom: "16px" }}>
                    {data.title}
                </Typography>
                <Typography variant="h6" sx={{ marginBottom: "20px" }}>
                    {data.description}
                </Typography>

                <Box sx={{ display: "flex", alignItems: "center", justifyContent: "center" }}>
                    <Typography variant="h4" sx={{ marginRight: "16px", fontSize: "30px" }}> {/* Aumentar tamaño del número */}
                        {progress}%
                    </Typography>
                    <LinearProgress
                        variant="determinate"
                        value={progress}
                        sx={{
                            width: "60%", // Aumentar el tamaño de la barra de progreso
                            marginTop: "10px",
                            height: "20px", // Aumentar grosor de la barra de progreso
                            borderRadius: "10px" // Hacer bordes más suaves
                        }}
                    />
                </Box>


                <Box sx={{ marginTop: "30px" }}>
                    {/* Mostrar 3 preguntas al mismo tiempo */}
                    {[...Array(3)].map((_, idx) => {
                        const questionIndex = currentQuestionIndex + idx;
                        if (questionIndex < data.questions.length) {
                            return (
                                <Box key={questionIndex} sx={{ marginBottom: "20px" }}>
                                    <Typography variant="h5" sx={{ marginBottom: "10px" }}>
                                        {data.questions[questionIndex]}
                                    </Typography>

                                    <Box sx={{ display: "flex", justifyContent: "center", alignItems: "center" }}>
                                        <Typography variant="h5" sx={{ marginRight: "20px", fontSize: "2rem" }}>
                                            Estoy de acuerdo
                                        </Typography>
                                        {Array.from({ length: 7 }, (_, index) => {
                                            const size = 40 + 10 * Math.abs(3 - index);
                                            return (
                                                <Box
                                                    key={index}
                                                    onClick={() => handleCircleClick(questionIndex, index)}
                                                    sx={{
                                                        width: `${size}px`,
                                                        height: `${size}px`,
                                                        borderRadius: "50%",
                                                        border: `4px solid ${selectedCircles[questionIndex] === index ? pinkTheme.palette.primary.main : "#F8BBD0"}`,
                                                        backgroundColor: selectedCircles[questionIndex] === index ? pinkTheme.palette.primary.main : "transparent",
                                                        margin: "0 10px",
                                                        cursor: "pointer",
                                                    }}
                                                />
                                            );
                                        })}
                                        <Typography variant="h5" sx={{ marginLeft: "20px", fontSize: "2rem" }}>
                                            No estoy de acuerdo
                                        </Typography>
                                    </Box>
                                </Box>
                            );
                        }
                        return null;
                    })}
                </Box>

                <Box sx={{ marginTop: "20px" }}>
                    {/* Botones de Siguiente y Regresar */}
                    <Box sx={{ display: "flex", gap: "10px" }}>
                        <Button
                            variant="contained"
                            color="primary"
                            onClick={handleNext}
                            sx={{
                                flex: 1,
                                borderRadius: "50px", // Borde circular
                                padding: "10px 20px", // Asegurando que sea más grande
                                textTransform: "none",
                            }}
                            disabled={currentQuestionIndex + 3 >= data.questions.length || !areAllQuestionsAnswered()} // Deshabilitar cuando no hay más preguntas
                        >
                            Siguiente
                        </Button>

                        <Button
                            variant="outlined"
                            sx={{
                                flex: 1,
                                borderRadius: "50px", // Borde circular
                                padding: "10px 20px", // Asegurando que sea más grande
                                textTransform: "none",
                                borderColor: pinkTheme.palette.primary.main,
                                color: pinkTheme.palette.primary.main,
                            }}
                            onClick={handleBack}
                            disabled={currentQuestionIndex <= 0} // Deshabilitar cuando no se puede retroceder
                        >
                            Regresar
                        </Button>
                    </Box>

                    {/* Botón de Ver resultados en su propia fila */}
                    <Box sx={{ marginTop: "20px" }}>
                        <Button
                            variant="contained"
                            color="primary"
                            onClick={handleFinish}
                            sx={{
                                borderRadius: "50px", // Borde circular
                                padding: "10px 20px", // Asegurando que sea más grande
                                textTransform: "none",
                                width: "100%", // Ocupa toda la fila
                            }}
                            disabled={!areAllQuestionsAnswered2()} // Habilitar solo cuando todas las preguntas han sido respondidas
                        >
                            Ver mis resultados
                        </Button>
                    </Box>
                </Box>

            </Box>
        </Box>
    );
};

export default Preguntas;
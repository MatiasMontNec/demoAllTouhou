// Guardar en sessionStorage con un key específico para cada test
export function saveToSessionStorage(testId, key, value) {
    try {
        const data = typeof value === "string" ? value : JSON.stringify(value);
        sessionStorage.setItem(`${testId}-${key}`, data); // Usamos el id para diferenciar las claves
    } catch (error) {
        console.error(`Error saving ${key} to sessionStorage:`, error);
    }
}

// Cargar desde sessionStorage con un key específico para cada test
export function loadFromSessionStorage(testId, key, defaultValue = null) {
    try {
        const savedValue = sessionStorage.getItem(`${testId}-${key}`); // Usamos el id para buscar la clave específica
        return savedValue ? JSON.parse(savedValue) : defaultValue;
    } catch (error) {
        console.error(`Error loading ${key} from sessionStorage:`, error);
        return defaultValue;
    }
}

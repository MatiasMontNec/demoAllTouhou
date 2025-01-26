export function saveToSessionStorage(key, value) {
    try {
        const data = typeof value === "string" ? value : JSON.stringify(value);
        sessionStorage.setItem(key, data);
    } catch (error) {
        console.error(`Error saving ${key} to sessionStorage:`, error);
    }
}

export function loadFromSessionStorage(key, defaultValue = null) {
    try {
        const savedValue = sessionStorage.getItem(key);
        // Asegurarse de que el valor exista y sea válido JSON si no es string
        return savedValue ? JSON.parse(savedValue) : defaultValue;
    } catch (error) {
        console.error(`Error loading ${key} from sessionStorage:`, error);
        return defaultValue;
    }
}

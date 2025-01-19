import axios from "axios";

const demoAllTouhouBackendServer = import.meta.env.VITE_DEMOALLTOUHOU_BACKEND_SERVER;
const demoAllTouhouBackendPort = import.meta.env.VITE_DEMOALLTOUHOU_BACKEND_PORT;

export default axios.create({
    baseURL: `http://${demoAllTouhouBackendServer}:${demoAllTouhouBackendPort}`,
    headers: {
        'Content-Type': 'application/json'
    }
});
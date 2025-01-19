import './App.css';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Home from "./components/Home.jsx";
import Navbar from "./components/NavBar.jsx";
import NotFound from "./components/NotFound.jsx";
import theme from "./theme";
import {ThemeProvider} from "@mui/material";

function App() {
    return (
        <Router>
            <ThemeProvider theme={theme}>
                <div className="container">
                    <Navbar></Navbar>
                    <Routes>
                        <Route path="" element={<Home/>}/>
                        <Route path="*" element={<NotFound/>}/>
                    </Routes>
                </div>
            </ThemeProvider>
        </Router>
    );
}

export default App;

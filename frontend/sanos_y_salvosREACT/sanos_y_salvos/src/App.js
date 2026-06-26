import logo from './logo.svg';
import './App.css';
import React from 'react';
import Pag_RegistroUsuario from './paginas/Pag_RegistroUsuario';
import Pag_inicioSesion from './paginas/Pag_inicioSesion';
import Home from './paginas/Home';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Pag_RegistroMascota from './paginas/Pag_RegistroMascota';
import Pag_Avistamiento from './paginas/Pag_Avistamiento';
function App() {
  return (
    <div>
      <Router>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/Pag_RegistroMascota" element={<Pag_RegistroMascota />} />
          <Route path="/Pag_RegistroUsuario" element={<Pag_RegistroUsuario />} />
          <Route path="/Pag_inicioSesion" element={<Pag_inicioSesion />} />
          <Route path="/avistamiento/:id" element={<Pag_Avistamiento />} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;

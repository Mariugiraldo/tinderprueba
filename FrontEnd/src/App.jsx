
import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { requireAuth } from './components/utils/Auth'; // Asegúrate de ajustar la ruta según tu estructura de archivos

// const ProtectedHome = requireAuth(Home);

function App() {
  return (
    <BrowserRouter>
      <Routes>
        { /**
         * <Route path="/" element={<Login />} />
          <Route path="/home" element={<ProtectedHome />} />
         * */ }
      </Routes>
    </BrowserRouter>
  );
}

export default App;

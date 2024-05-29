import React from "react";
import { Navigate } from "react-router-dom";

// Esta funcion nos ayudara a verificar si el usuario está autenticado...
export const isAuthenticated = () => { // la exportamos para su uso...
    const token = localStorage.getItem('token'); 
    // devolvemos true si el token de sesión está presente y es válido...
    return token !== null && token !== undefined; 
};

// middleware para verificar la autenticación en cada cambio de ruta...
export const requireAuth = (Component) => { // la exportamos para su uso...
    return (props) => {
        if(!isAuthenticated()){
            // redireccionara al usuario al login de sesión si no esta autenticado...
            return < Navigate to='/' />;
        }
        // renderiza el componente protegido si el usuario está autenticado...
        return <Component {...props}/>; 
    };
};

/**
 * Hola Macarena o Reynaldo, aquí les dejo solo por si las dudas un caso de uso: 
 * 
 * @import { requireAuth } from './auth'; 
 * 
 * @ejemplo de un componte protegido...
 * const ProtectedComponent = () => {
 *  return <div>Contenido protegido</div>;
 * }
 * 
 * @aplicacion del middleware de auth...
 * const ProtectedRoute = requireAuth(ProtectedComponent); 
*/
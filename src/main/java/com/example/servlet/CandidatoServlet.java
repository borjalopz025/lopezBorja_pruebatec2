package com.example.servlet;

import com.example.controller.CandidatoController;
import com.example.exceptions.ExcepcionPersonalizada;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/candidato")
public class CandidatoServlet extends HttpServlet {

    CandidatoController candidatoController = new CandidatoController();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("candidatoForm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Obtiene los parámetros del formulario enviados por el usuario (n
        String nombre = req.getParameter("nombre");
        String apellido = req.getParameter("apellido");

        try {
            // Llama al controlador para crear un nuevo candidato con los datos proporcionados
            candidatoController.create(nombre, apellido);

            // Si va bien, redirige al usuario al formulario de turnos
            resp.sendRedirect(req.getContextPath() + "/turnos");

        } catch (Exception e) {
            // Si ocurre algún error, muestra un mensaje de error en la página de error
            req.setAttribute("error", "Ha habido un error: " + e.getMessage());
            req.getRequestDispatcher("/paginaError.jsp").forward(req, resp);
        }
    }
}

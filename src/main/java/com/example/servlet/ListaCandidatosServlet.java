package com.example.servlet;

import com.example.controller.CandidatoController;
import com.example.controller.TurnosController;
import com.example.entities.Candidato;
import com.example.entities.Turnos;
import com.example.exceptions.ExcepcionPersonalizada;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@WebServlet(urlPatterns = "/lista") // Define la URL de acceso al servlet
public class ListaCandidatosServlet extends HttpServlet {


    CandidatoController candidatoController = new CandidatoController();
    TurnosController turnosController = new TurnosController();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Candidato> listaCandidatos = null;
        try {

            listaCandidatos = candidatoController.listaCandidatos();

        } catch (Exception e) {
            // Si ocurre un error al obtener la lista de candidatos, lanza una excepción
            throw new RuntimeException("Hubo un error: " + e.getMessage());
        }


        req.setAttribute("listaCandidatos", listaCandidatos);

        // Redirige a la página listaCandidatos.jsp para mostrar los candidatos
        req.getRequestDispatcher("listaCandidatos.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Obtiene los parámetros de búsqueda enviados desde el formulario
        String estado = req.getParameter("estado");
        String fecha = req.getParameter("fecha");
        SimpleDateFormat formaFecha = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaDefinitiva = null;

        try {
            // Intenta parsear la fecha recibida en el formato esperado (yyyy-MM-dd)
            fechaDefinitiva = formaFecha.parse(fecha);

            // Llama al controlador para filtrar los turnos por fecha y estado
            Optional<List<Turnos>> listaDeTurnosFiltrados = turnosController.filtrarTurnos(fechaDefinitiva, estado);

            // Si hay turnos que cumplen con los criterios de filtrado, los agrega como atributo en la solicitud
            if (listaDeTurnosFiltrados.isPresent()) {
                req.setAttribute("listaDeTurnosFiltrados", listaDeTurnosFiltrados.get());
            } else {
                // Si no se encontraron turnos, agrega un mensaje indicando que no se encontraron
                req.setAttribute("mensaje", "No se encontraron turnos.");
            }

            // Redirige a la página listaTurnos.jsp para mostrar los resultados de la búsqueda
            req.getRequestDispatcher("listaTurnos.jsp").forward(req, resp);

        } catch (Exception e) {
            // Si ocurre un error durante el proceso, muestra un mensaje de error
            req.setAttribute("mensaje", "Ha habido un error: " + e.getMessage());
            req.getRequestDispatcher("paginaError.jsp").forward(req, resp);
        }
    }
}

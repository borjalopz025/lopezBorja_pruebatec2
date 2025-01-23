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

@WebServlet(urlPatterns = "/lista")
public class ListaCandidatosServlet extends HttpServlet {

    CandidatoController candidatoController = new CandidatoController();
    TurnosController turnosController = new TurnosController();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Candidato> listaCandidatos = null;
        try {
            listaCandidatos = candidatoController.listaCandidatos();

        } catch (Exception e) {
            throw new RuntimeException("Hubo un error: " + e.getMessage());
        }

        req.setAttribute("listaCandidatos", listaCandidatos);

        req.getRequestDispatcher("listaCandidatos.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String estado = req.getParameter("estado");
        String fecha = req.getParameter("fecha");
        SimpleDateFormat formaFecha = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaDefinitiva = null;

        try {

            fechaDefinitiva = formaFecha.parse(fecha);

            // Llamar al controlador para filtrar turnos
            Optional<List<Turnos>> listaDeTurnosFiltrados = turnosController.filtrarTurnos(fechaDefinitiva, estado);

            if (listaDeTurnosFiltrados.isPresent()) {
                req.setAttribute("listaDeTurnosFiltrados", listaDeTurnosFiltrados.get());
            } else {
                req.setAttribute("mensaje", "No se encontraron turnos ");
            }

            req.getRequestDispatcher("listaTurnos.jsp").forward(req, resp);

        } catch (Exception e) {

            req.setAttribute("mensaje", "ha habido un error: " + e.getMessage());
            req.getRequestDispatcher("paginaError.jsp").forward(req, resp);
        }
    }

}

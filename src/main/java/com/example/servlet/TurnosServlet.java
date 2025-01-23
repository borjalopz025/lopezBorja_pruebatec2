package com.example.servlet;

import com.example.controller.CandidatoController;
import com.example.controller.TurnosController;
import com.example.entities.Candidato;
import com.example.entities.Turnos;
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

@WebServlet(urlPatterns = "/turnos") // Define la URL para acceder al servlet
public class TurnosServlet extends HttpServlet {

    // Controladores para gestionar la lógica de negocios
    TurnosController turnosController = new TurnosController();
    CandidatoController candidatoController = new CandidatoController();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Obtiene la lista de candidatos desde el controlador
            List<Candidato> listaCandidatos = candidatoController.listaCandidatos();
            // Pone la lista de candidatos en el atributo de la solicitud para que esté disponible en la vista
            req.setAttribute("listaCandidatos", listaCandidatos);
            // Redirige a la página del formulario para crear turnos
            req.getRequestDispatcher("turnosForm.jsp").forward(req, resp);
        } catch (Exception e) {
            // Si ocurre un error al cargar los candidatos, se muestra un mensaje de error en la vista de error
            req.setAttribute("error", "Hubo un error al cargar los candidatos.");
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Obtiene los parámetros del formulario enviados por el usuario
        String estado = req.getParameter("estado");
        Integer numero = Integer.valueOf(req.getParameter("numero"));
        String fecha = req.getParameter("fecha");
        Long candidatoId = Long.valueOf(req.getParameter("candidatoId"));

        // Define el formato de fecha esperado (yyyy-MM-dd)
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha2;

        // Intenta parsear la fecha del formulario a un objeto Date
        try {
            fecha2 = formatoFecha.parse(fecha);
        } catch (ParseException e) {
            // Si la fecha no tiene el formato correcto, lanza una excepción
            throw new RuntimeException(e);
        }

        try {
            // Verifica si el número de turno ya existe
            if (turnosController.existeNumeroTurno(numero)) {
                req.setAttribute("error", "El número ya existe, introduce otro.");
                req.getRequestDispatcher("turnosForm.jsp").forward(req, resp);
            }

            // Crea un objeto Candidato con el ID recibido del formulario
            Candidato candidatoIdTurno = new Candidato();
            candidatoIdTurno.setId(candidatoId);

            // Llama al controlador para crear un nuevo turno con los datos proporcionados
            turnosController.create(estado, numero, fecha2, candidatoIdTurno);

            // Si va bien, redirige a la lista de turnos
            resp.sendRedirect(req.getContextPath() + "/lista");

        } catch (Exception e) {
            // Si ocurre algún error al crear el turno, muestra el mensaje de error
            req.setAttribute("error", "Ha habido un error: " + e.getMessage());
            req.getRequestDispatcher("paginaError.jsp").forward(req, resp);
        }
    }
}

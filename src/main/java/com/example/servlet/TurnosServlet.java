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

@WebServlet(urlPatterns = "/turnos")
public class TurnosServlet extends HttpServlet {

    TurnosController turnosController = new TurnosController();
    CandidatoController candidatoController = new CandidatoController();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            List<Candidato> listaCandidatos = candidatoController.listaCandidatos();
            req.setAttribute("listaCandidatos", listaCandidatos);
            req.getRequestDispatcher("turnosForm.jsp").forward(req, resp);

        } catch (Exception e) {

            req.setAttribute("error", "Hubo un error al cargar los candidatos.");
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String estado = req.getParameter("estado");
        Integer numero = Integer.valueOf(req.getParameter("numero"));
        String fecha = req.getParameter("fecha");
        Long candidatoId = Long.valueOf(req.getParameter("candidatoId"));
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha2;

//        me obliga el intelije por el .parse
        try {
            fecha2 = formatoFecha.parse(fecha);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


        try {

            Candidato candidatoIdTurno = new Candidato();
            candidatoIdTurno.setId(candidatoId);

            turnosController.create(estado, numero, fecha2, candidatoIdTurno);

            resp.sendRedirect(req.getContextPath() + "/lista");

        } catch (Exception e) {

            req.setAttribute("error", "Ha habido un error " + e.getMessage());
            req.getRequestDispatcher("paginaError.jsp").forward(req, resp);
        }
    }
}

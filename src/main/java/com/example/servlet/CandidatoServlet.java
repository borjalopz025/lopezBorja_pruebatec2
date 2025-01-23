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

        String nombre = req.getParameter("nombre");
        String apellido = req.getParameter("apellido");


        try {

            candidatoController.create(nombre, apellido);

            resp.sendRedirect( req.getContextPath() + "/turnos");

        } catch (Exception e) {

            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/paginaError.jsp").forward(req, resp);
        }

    }
}

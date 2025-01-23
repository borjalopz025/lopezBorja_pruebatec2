<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.example.entities.Candidato, com.example.entities.Turnos" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <%@ include file="partials/header.jsp" %>
    <link rel="stylesheet" href="./public/css/style.css">
</head>
<body>

    <h1>Introduce el turno</h1>

    <br><br>
    <form action="/app/turnos" method="post">

            <label for="opciones">Selecciona una opción:</label> <br>
            <select id="opciones" name="candidatoId" required>
                <option value="" disabled selected>-- Selecciona una opción --</option>
                    <% List<Candidato> listaCandidatos = (List<Candidato>) request.getAttribute("listaCandidatos");

                        for (Candidato candidato: listaCandidatos) { %>
                            <option value="<%= candidato.getId() %>"><%= candidato.getNombre() %> <%= candidato.getApellido() %></option>
                    <% } %>
            </select>

        <br><br>

        <select id="opciones" name="estado" required>
                    <option value="espera" selected>-- Turno en espera --</option>
                    <option value="atendido">-- Turno atendido --</option>
                </select>

        <br><br>

        <input type="number" name="numero" id="numero" placeholder="Numero del turno" required>

        <br><br>

        <input type="date" name="fecha" id="fecha" placeholder="introduce la fecha del turno" required>

        <br><br>

        <button type="submit">Guardar Persona</button>

    </form>

</body>
</html>

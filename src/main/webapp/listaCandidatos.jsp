<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="java.util.List, com.example.entities.Candidato,  com.example.entities.Turnos" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <%@ include file="partials/header.jsp" %>
    <link rel="stylesheet" href="./public/css/style.css">

</head>
<body>

    <h1>Filtrar turnos</h1>

    <form action="/app/lista" method="post">

        <input type="date" name="fecha" id="fecha" required>

        <br></br>

        <select name="estado" id="estado">
            <option value="espera" selected>-- Turno en espera --</option>
            <option value="atendido">-- Turno atendido --</option>
        </select>

        <br></br>

        <button type="submit">Filtrar</button>

    </form>

    

    <h1>Lista de Candidatos</h1>

<table>
    <thead>
        <tr>
            <th>Nombre del Candidato</th>
            <th>Apellido del Candidato</th>
            <th>Número de Turno</th>
            <th>Fecha del Turno</th>
            <th>Estado del Turno</th>
        </tr>
    </thead>
    <tbody>
        <% 
            List<Candidato> listaCandidatos = (List<Candidato>) request.getAttribute("listaCandidatos");

            // Iterar sobre la lista de candidatos
            for (Candidato candidato : listaCandidatos) { 
                for (Turnos turno : candidato.getTurnos()) {
                    String fecha = turno.getFecha().toString().split(" ")[0]; // Solo la fecha, sin hora
        %>
        <tr>
            <td><%= candidato.getNombre() %></td>
            <td><%= candidato.getApellido() %></td>
            <td><%= turno.getNumero() %></td>
            <td><%= fecha %></td>
            <td><%= turno.getEstado() %></td>
        </tr>
        <% 
                }
            }
        %>
    </tbody>
</table>

</body>
</html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.example.entities.Turnos" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Turnos Filtrados</title>
    <%@ include file="partials/header.jsp" %>
    <link rel="stylesheet" href="./public/css/style.css">
</head>
<body>

    <h1>Lista de Turnos Filtrados</h1>
    
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
                // Obtener la lista de turnos filtrados de la solicitud
                List<Turnos> turnosFiltrados = (List<Turnos>) request.getAttribute("listaDeTurnosFiltrados");

                // Iterar sobre los turnos y mostrar la información en la tabla
                for (Turnos turno : turnosFiltrados) {
                    String fechaFormateada = new java.text.SimpleDateFormat("yyyy-MM-dd").format(turno.getFecha());
            %>
            <tr>
                <td><%= turno.getCandidatos().getNombre() %></td>
                <td><%= turno.getCandidatos().getApellido() %></td>
                <td><%= turno.getNumero() %></td>
                <td><%= fechaFormateada %></td>
                <td><%= turno.getEstado() %></td>
            </tr>
            <% 
                }
            %>
        </tbody>
    </table>

</body>
</html>

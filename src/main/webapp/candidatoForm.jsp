<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

    <h1>Introduce los datos del candidato</h1>

    <form action="/app/candidato" method="post">

        <input type="text" name="nombre" id="nombre" placeholder="Ingrese el nombre" required >

        <br><br>

        <input type="text" name="apellido" id="apellido"  placeholder="Ingrese el apellido" required>

        <br><br>

        <button type="submit">Guardar Candidato</button>

    </form>



</body>
</html>

<%-- 
    Document   : ConsultaDeSaldoActual
    Created on : May 11, 2022, 9:55:56 PM
    Author     : sebashdez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Seleccionar formato de bitacora</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
    </head>
    
    <body>
    <div class="row">
        <div class="col-md-8 mx-auto">
            <div class="card mt-8 text-center">
                <div class="card-header">
                    <h1>Seleccionar formato de bitacora</h1>
                    <div>
                        <img src="../imagenesVista/Imagen16.png"/>
                    </div>
                </div>
                <br>
                <form method="post" action="<c:url value="/vistaWeb/SeleccionConsultaDeBitacora"/>" >
                    <div class="box" style="text-align: center">
                        <input type="submit" class= "btn btn-primary " name="event" value="Consulta por acciones de hoy">
                        <input type="submit" class= "btn btn-secondary " name="event" value="Consulta por acciones de la vista CLI">
                        <input type="submit" class= "btn btn-success " name="event" value="Consulta por acciones de la vista GUI">
                        <input type="submit" class= "btn btn-danger " name="event" value="Consulta por acciones de la vista WEB">
                        <input type="submit" class= "btn btn-warning " name="event" value="Consulta de todos las acciones">
                    </div>
                    <br>
                    <label>Elija el formato</label>
                        <select name="formatos">
                        <option selected>XML</option>
                        <option>CSV</option>
                        <option>TXT</option>
                        </select>
                    <a href="../index.html">Cancelar</a>
                </form>
            </div>
        </div>
    </div>
    </body>
</html>
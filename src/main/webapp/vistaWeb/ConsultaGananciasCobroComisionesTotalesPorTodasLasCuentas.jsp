<%-- 
    Document   : ConsultaGananciasCobroComisionesPorCadaCuenta
    Created on : May 11, 2022, 9:56:30 PM
    Author     : sebashdez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Ganancias totales de cada cuenta por cobro de comision </title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
    </head>
    
    <body>
        <div class="card-body text-center">
            <div class ="mb-4">
                <h1> Ganancias totales de cada cuenta por cobro de comision </h1>
                <div>
                    <img src="../imagenesVista/Imagen25.png"/>
                </div>
                <br>
            </div>
        </div>
    <table class="table table-striped text-center">
        <thead>
            <tr>
                <th>Cuenta</th>
                <th>Total depositos</th>
                <th>Total retiros</th>
                <th>Total de depositos y retiros</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="row" items="${operacionesAMostrar}">
            <tr>
                <td>${row.numeroCuenta}</td>
                <td>${row.depositos}</td>                        
                <td>${row.retiros}</td>
                <td>${row.depositosYRetiros}</td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
    <div class="form-group text-center">
        <a href="../index.html">Cancelar</a>
    </div>
    </body>
</html>

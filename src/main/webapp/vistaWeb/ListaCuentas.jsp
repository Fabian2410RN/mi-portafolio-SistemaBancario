<%-- 
    Document   : ListaCuentas
    Created on : May 11, 2022, 10:00:51 PM
    Author     : sebashdez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de cuentas</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
    </head>
    
    <body>
    <table class="table table-striped">
        <div class="card-body">
            <div class ="mb-4 text-center" >
                <h1> Lista de cuentas </h1>
                <div>
                    <img src="../imagenesVista/Imagen3.png"/>
                </div>
                <thead>
                    <tr>
                        <th>Numero de cuenta</th>
                        <th>Estatus</th>
                        <th>Saldo</th>
                        <th>Propietario</th>
                        <th>Identificacion</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="row" items="${dtos}">
                        <tr>
                            <td><a href="DetalleCuenta?numeroCuenta=${row.numeroCuenta}">${row.numeroCuenta}</a></td>
                            <td>${row.estatus}</td>
                            <td>${row.saldo}</td>
                            <td>${row.propietario}</td>
                            <td>${row.identificacion}</td>
                            
                        </tr>
                    </c:forEach>
                        
                </tbody>
            </div>
        </div>
    </table>
    <div class="form-group text-center">
        
        <a href="../index.html">Cancelar</a>
    </div>
    </body>
</html>

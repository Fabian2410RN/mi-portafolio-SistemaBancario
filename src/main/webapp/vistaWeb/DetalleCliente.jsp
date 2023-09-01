<%-- 
    Document   : DetalleCliente
    Created on : May 11, 2022, 9:59:41 PM
    Author     : sebashdez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cliente</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
    </head>
    
    <body>
    <table class="table table-striped">
        <div class="card-body">
            <div class ="mb-4">
                <div class = " mb-4 text-center">
                    <h1> Cliente </h1>
                    <div>
                        <img src="../imagenesVista/Imagen5.png"/>
                    </div>
                    <br>
                    <div class="row">
                        <div class="form-group col-md-6">
                            <div class="card mb-2">
                                <div class="card-body">
                                    <h2> Detalles </h2>
                                    <div class="card-header">
                                        <p class = "lead"> Codigo: ${codigo} </p>
                                        <p class = "lead"> Primer apellido: ${primerApellido} </p>
                                        <p class = "lead"> Segundo apellido: ${segundoApellido} </p>
                                        <p class = "lead"> Nombre: ${nombre} </p>
                                        <p class = "lead"> Identificacion: ${identificacion} </p>
                                        <p class = "lead"> Fecha nacimiento: ${fechaNacimiento}</p>
                                        <p class = "lead"> Numero telefono: ${numeroTelefono} </p>
                                        <p class = "lead"> Correo electronico: ${correoElectronico} </p> 
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group col-md-6">
                            <div class="card mb-2">
                                <div class="card-body">
                                    <h2> Cuentas </h2>
                                    <div class="card-header">
                                        <c:forEach var="col" items="${cuentasAsociadas}">
                                            <p class = "lead"> ${col.numeroDeCuenta}</p>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </table>
    <div class="form-group text-center">
        <a href="../index.html">Volver</a>
    </div>
    </body>
</html>

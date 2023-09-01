<%-- 
    Document   : DetalleCuenta
    Created on : May 11, 2022, 9:59:59 PM
    Author     : sebashdez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consulta de bitácora</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
    </head>
    
    <body>
        <table class="table table-striped">
        <div class="card-body">
            <div class ="mb-4">
                <div class = "mb-4 text-center">
                    <h1> Consulta de bitácora </h1>
                    <div>
                        <img src="../imagenesVista/Imagen25.png"/>
                    </div>

                    <br>
                    <div class="row text-center">
                        <div class="form-group col">
                            <div class="card">
                                <div class="card-body">
                                    <div class="card-header">
                                        <input type="hidden" name = "formato" value="${formato}" />
                                        <p class = "lead"> ${bitacora} </p>
                                     
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
        <a href="SeleccionConsultaDeBitacora">Volver</a>
    </div>       
    </body>
</html>

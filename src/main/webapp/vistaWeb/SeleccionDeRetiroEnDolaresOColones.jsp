<%-- 
    Document   : SeleccionDeRetiroEnDolaresOColones
    Created on : May 11, 2022, 10:01:53 PM
    Author     : sebashdez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Retiro en Dolares o Colones</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
    </head>
    
    <body>
    <div class="row">
        <div class="col-md-8 mx-auto">
            <div class="card mt-8 text-center">
                <div class="card-header">
                    <h1>Retiro en</h1>
                    <div>
                        <img src="../imagenesVista/Imagen10.png"/>
                    </div>
                </div>
                <br>
                <form method="post" action="<c:url value="/vistaWeb/SeleccionDeRetiroEnDolaresOColones"/>" >
                    <input type="hidden" name = "numeroDeCuenta" value="${numeroDeCuenta}" />
                    <div class="form-group">
                        <input type="submit" class= "btn btn-primary " name="event" value="Colones">
                    </div>
                    <div class="form-group">
                        <input type="submit" class= "btn btn-primary " name="event" value="Dolares">   
                    </div>
                    <div class="box" style="text-align: center">
                        <a href="../index.html">Cancelar</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
    </body>
</html>

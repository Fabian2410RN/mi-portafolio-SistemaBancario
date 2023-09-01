<%-- 
    Document   : ConsultaEstadoDeCuenta
    Created on : May 11, 2022, 9:56:05 PM
    Author     : sebashdez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Estado de cuenta</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
    </head>
    
    <body>
    <div class="row">
        <div class="col-md-8 mx-auto">
            <div class="card mt-8 text-center">
                <div class="card-header">
                    <h1>Estado de cuenta</h1>
                    <div>
                        <img src="../imagenesVista/Imagen17.png"/>
                    </div>
                </div>
                <br>
                <form method="post" action="<c:url value="/vistaWeb/ConsultaEstadoDeCuenta"/>" >
                    <div class="form-group">
                        <label>Numero de cuenta</label>
                        <input type="text" class "form-control" name = "numeroCuenta" placeholder="Numero de cuenta" required>
                    </div>
                    <div class="form-group">
                        <label>Pin</label>
                        <input type="password" class "form-control" name = "pin" placeholder="Pin" required>
                    </div>
                    <div class="box" style="text-align: center">
                        <button type="submit" class= "btn btn-primary">
                            Consultar
                        </button>
                        <a href="../index.html">Cancelar</a> 
                    </div>
                </form>
            </div>
        </div>
    </div>
    </body>
</html>

<%-- 
    Document   : SolicitarMontoDeRetiroEnColonesTerceraEtapa
    Created on : May 11, 2022, 10:02:04 PM
    Author     : sebashdez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
    </head>
    
    <body>
    <div class="row">
        <div class="col-md-8 mx-auto">
            <div class="card mt-8 text-center">
                <div class="card-header">
                    <h1>Retiro en colones</h1>
                    <div>
                        <img src="../imagenesVista/Imagen10.png"/>
                    </div>
                </div>
                <br>
                <form method="post" action="<c:url value="/vistaWeb/SolicitarMontoDeRetiroEnColonesTerceraEtapa"/>" >
                    <div class="form-group">
                        <label>Monto</label>
                        <input type="number" class "form-control" name = "montoRetirado" placeholder="Monto a retirar" required>
                        <input type="hidden" name = "numeroDeCuenta" value="${numeroDeCuenta}" />
                    </div>
                    <div class="box" style="text-align: center">
                        <button type="submit" class= "btn btn-primary">
                            Retirar
                        </button>
                        <a href="../index.html">Cancelar</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
    </body>
</html>

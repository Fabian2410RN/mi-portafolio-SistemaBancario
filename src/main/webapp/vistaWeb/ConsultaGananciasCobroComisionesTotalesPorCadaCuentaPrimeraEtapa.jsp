<%-- 
    Document   : ConsultaDeEstatusCuenta
    Created on : May 11, 2022, 9:52:28 PM
    Author     : sebashdez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consulta de ganancias de cobro comisiones totales por cada cuenta</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
    </head>
    
    <body>
    <div class="row">
        <div class="col-md-8 mx-auto">
            <div class="card mt-8 text-center">
                <div class="card-header">
                    <h1>Consulta de ganancias de cobro comisiones totales por cada cuenta</h1>
                    <div>
                        <img src="../imagenesVista/Imagen26.png"/>
                    </div>
                </div>
                <br>
                <form method="post" action="<c:url value="/vistaWeb/ConsultaGananciasCobroComisionesTotalesPorCadaCuentaPrimeraEtapa"/>" >
                    <div class="form-group">
                        <label>Numero de cuenta</label>
                        <input type="text" class "form-control" name = "numeroCuenta" placeholder="Numero de cuenta" required>
                        <br>
                        <c:if test="${error != null}">
                            <span>${error}</span>
                        </c:if>
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

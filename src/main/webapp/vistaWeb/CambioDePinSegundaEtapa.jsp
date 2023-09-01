<%-- 
    Document   : CambioDePinSegundaEtapa
    Created on : May 11, 2022, 9:50:33 PM
    Author     : sebashdez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cambio de pin</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
    </head>
    
    <body>
    <div class="row">
        <div class="col-md-8 mx-auto">
            <div class="card mt-8 text-center">
                <div class="card-header">
                    <h1>Cambio de pin</h1>
                    <div>
                        <img src="../imagenesVista/Imagen12.png"/>
                    </div>
                </div>
                <br>
                <form method="post" action="<c:url value="/vistaWeb/CambioDePinSegundaEtapa"/>" >
                    <div class="form-group">
                        <label>Digitar pin</label>
                        <input type="password" class "form-control" name = "pinActual" placeholder="Pin actual" required />
                        <input type="hidden" name = "numeroDeCuenta" value="${numeroDeCuenta}" />
                        <br>
                        <c:if test="${error != null}">
                            <span>${error}</span>
                            <input type="hidden" name = "numeroDeCuenta" value="${numeroDeCuenta}" />
                        </c:if>
                    </div>
                    <div class="form-group">
                        <button type="submit" class= "btn btn-primary">
                            Aceptar
                        </button>
                        <a href="../index.html">Cancelar</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
    </body>
</html>

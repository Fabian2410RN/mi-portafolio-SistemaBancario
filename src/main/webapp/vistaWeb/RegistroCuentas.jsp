<%-- 
    Document   : RegistroCuentas
    Created on : May 10, 2022, 10:53:22 PM
    Author     : sebashdez
    
    final String pin = (String) request.getAttribute("pin");
    final String montoInicial = (String) request.getAttribute("montoInicial");
    final String identificacionCliente = (String) request.getAttribute("identificacionCliente");

--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro de cuenta</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
    </head>
    
    <body>
    <div class="row">
        <div class="col-md-8 mx-auto">
            <div class="card mt-8 text-center">
                <div class="card-header">
                    <h1>Registro de cuenta</h1>
                    <div>
                        <img src="../imagenesVista/Imagen19.png"/>
                    </div>
                </div>
                <br>
                <form method="post" action="<c:url value="/vistaWeb/RegistroCuentas"/>" >
                    <div class="form-group">
                        <label>Pin</label>
                        <input type="password" class "form-control" name = "pin" placeholder="Pin" required>
                    </div>
                    <div class="form-group">
                        <label>Monto inicial</label>
                        <input type="number" class "form-control" name = "montoDepositoInicial" placeholder="Monto inicial" required>
                    </div>
                    <div class="form-group">
                        <label>Identificacion cliente</label>
                        <input type="number" class "form-control" name = "identificacionCliente" placeholder="Identificacion cliente" required>
                    </div>
                    <div class="form-group">
                        <button type="submit" class= "btn btn-primary">
                            Registrar
                        </button>
                        <a href="../index.html">Cancelar</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
    </body>
</html>

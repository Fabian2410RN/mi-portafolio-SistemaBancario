<%-- 
    Document   : SeleccionDeDeposito
    Created on : May 11, 2022, 10:01:35 PM
    Author     : sebashdez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tipo de deposito</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
    </head>
    
    <body>
    <div class="row">
        <div class="col-md-8 mx-auto">
            <div class="card mt-8 text-center">
                <div class="card-header">
                    <h1>Tipo de deposito</h1>
                    <div>
                        <img src="../imagenesVista/Imagen9.png"/>
                    </div>
                </div>
                <br>
                <form action="/" method="GET" >
                    <div class="form-group">
                        <a class="btn btn-primary " href="DepositoEnColones.jsp"> Colones </a> 
                    </div>
                    <div class="form-group">
                        <a class="btn btn-primary " href="DepositoConTipoDeCambio.jsp"> Dolares </a> 
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

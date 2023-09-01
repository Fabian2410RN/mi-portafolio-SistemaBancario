<%-- 
    Document   : ConsultaGananciasCobroComisionesDelBanco
    Created on : May 11, 2022, 9:56:15 PM
    Author     : sebashdez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ganancias totales de la cuenta por cobro de comisión</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
    </head>
    
    <body>
    <table class="table table-striped">
        <div class="card-body">
            <div class ="mb-4">
                <div class = " mb-4 text-center">
                    <h1> Ganancias totales de la cuenta por cobro de comision</h1>
                    <div>
                        <img src="../imagenesVista/Imagen26.png"/>
                    </div>
                    <br>
                    
                    <div class="row center-text">
                        <div class="col">
                            <p class = "lead"> Descripcion</p>
                            <p class = "lead"> Monto total por depositos: </p>
                            <p class = "lead"> Monto total por retiros:</p>
                            <p class = "lead"> Monto total por depositos y retiros:</p>
                                
                        </div>
                        <div class="col">
                            <p class = "lead"> Colones</p>
                            <p class = "lead"> ₡ ${montoDepositos}</p>
                            <p class = "lead"> ₡ ${montoRetiros}</p>
                            <p class = "lead"> ₡ ${montoDepositosYRetiros}</p> 
                        </div>
                        <div class="col">
                            <p class = "lead"> Dolares</p>
                            <p class = "lead"> $ ${montoDepositosDolares} </p>
                            <p class = "lead"> $ ${montoRetirosDolares}</p>
                            <p class = "lead"> $ ${montoDepositosYRetirosDolares}</p>
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
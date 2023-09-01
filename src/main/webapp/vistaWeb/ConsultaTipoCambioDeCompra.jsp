<%-- 
    Document   : ConsultaTipoCambioDeCompra
    Created on : May 11, 2022, 9:56:54 PM
    Author     : sebashdez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tipo de cambio compra</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
    </head>
    
    <body>
    <div class="row">
        <div class="col-md-8 mx-auto">
            <div class="card mt-8 text-center">
                <div class="card-header">
                    <h1>Tipo de cambio compra</h1>
                    <div>
                        <img src="../imagenesVista/Imagen13.png"/>
                    </div>
                </div>
                <br>
                <div class="form-group">
                    <table class="table table-striped">
                        <div class="card-body">
                            <h4>
                                1 dolar = ${tipoDeCambioCompra}
                            </h4>
                        </div>
                    </table>
                </div>
                <div class="form-group">
                    <a href="../index.html">Volver</a>
                </div>
            </div>
        </div>
    </div>
    </body>
</html>

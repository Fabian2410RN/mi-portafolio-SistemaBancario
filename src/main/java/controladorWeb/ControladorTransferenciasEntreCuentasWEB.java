/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladorWeb;

import controlador.MensajeEnPantallaCuenta;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import validacion.ValidacionCuenta;

/**
 *
 * @author estadm
 */
@WebServlet(name = "TransferenciasEntreCuentasWEB", urlPatterns = {"/vistaWeb/TransferenciasEntreCuentas"})
public class ControladorTransferenciasEntreCuentasWEB extends HttpServlet {
    private int cantidadDeIntentos;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("TransferenciasEntreCuentas.jsp").forward(request, response);
    }
    
  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            this.cantidadDeIntentos++;
        String numeroDeCuenta = request.getParameter("numeroCuenta");
        String pin = request.getParameter("pin");
        boolean existeCuenta = ValidacionCuenta.validarExisteCuenta(numeroDeCuenta);
        boolean cuentaEstaActiva = ValidacionCuenta.validarCuentaEstaActiva(numeroDeCuenta);
        if(existeCuenta) {
            if(cuentaEstaActiva) {
            boolean pinCorrespondeACuenta = ValidacionCuenta.validarPinCorrespondeACuenta(numeroDeCuenta, pin);
            if(pinCorrespondeACuenta) {
                response.sendRedirect("VerificacionMensajeDeTexto?numeroCuenta=" + numeroDeCuenta);
            }
            else {

                if(this.cantidadDeIntentos == 1) {
                    MensajeEnPantallaCuenta.imprimirMensajeDeErrorPinNoCorrespondeAAcuenta(numeroDeCuenta, pin);
                    response.sendRedirect("../index.html");
                }
                else {
                    validacion.ValidacionCuenta.inactivarCuenta(numeroDeCuenta);
                    response.sendRedirect("../index.html");
                }
                request.getRequestDispatcher("TransferenciasEntreCuentas.jsp").forward(request, response);
            }
            } else{
                MensajeEnPantallaCuenta.imprimirMensajeDeErrorCuentaInactiva();
                request.getRequestDispatcher("TransferenciasEntreCuentas.jsp").forward(request, response);
            }
        }
        else {
            request.getRequestDispatcher("TransferenciasEntreCuentas.jsp").forward(request, response);
            MensajeEnPantallaCuenta.imprimirMensajeDeErrorCuentaNoExiste(numeroDeCuenta);
            response.sendRedirect("../index.html");
        }
    }
}
    

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
 * @author sebashdez
 */
@WebServlet(name = "ControladorRetiroPrimeraEtapaWEB", urlPatterns = {"/vistaWeb/RetiroPrimeraEtapa"})
public class ControladorRetiroPrimeraEtapaWEB extends HttpServlet {
    private int cantidadDeIntentos;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("RetiroPrimeraEtapa.jsp").forward(request, response);
        cantidadDeIntentos = 0;
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
                response.sendRedirect("VerificacionMensajeDeTextoRetiro?numeroCuenta=" + numeroDeCuenta);
            }
            else {

                if(this.cantidadDeIntentos == 1) {
                    MensajeEnPantallaCuenta.imprimirMensajeDeErrorPinNoCorrespondeAAcuenta(numeroDeCuenta, pin);
                    request.getRequestDispatcher("RetiroPrimeraEtapa.jsp").forward(request, response);
                }
                else {
                    validacion.ValidacionCuenta.inactivarCuenta(numeroDeCuenta);
                    response.sendRedirect("../index.html");
                }
            }
            } else{
                MensajeEnPantallaCuenta.imprimirMensajeDeErrorCuentaInactiva();
                request.getRequestDispatcher("RetiroPrimeraEtapa.jsp").forward(request, response);
            }
        }
        else {
            MensajeEnPantallaCuenta.imprimirMensajeDeErrorCuentaNoExiste(numeroDeCuenta);
            request.getRequestDispatcher("RetiroPrimeraEtapa.jsp").forward(request, response);
        }
    }
}

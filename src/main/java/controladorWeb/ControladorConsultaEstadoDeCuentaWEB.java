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
@WebServlet(name = "ControladorConsultaEstadoDeCuentaWEB", urlPatterns = {"/vistaWeb/ConsultaEstadoDeCuenta"})
public class ControladorConsultaEstadoDeCuentaWEB extends HttpServlet {
    private int cantidadDeIntentos;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("ConsultaEstadoDeCuenta.jsp").forward(request, response);
        }
    
     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.cantidadDeIntentos++;
        String numeroDeCuenta = request.getParameter("numeroCuenta");
        String pin = request.getParameter("pin");
        boolean existeCuenta = ValidacionCuenta.validarExisteCuenta(numeroDeCuenta);
            if(existeCuenta) {
                boolean cuentaEstaActiva = ValidacionCuenta.validarCuentaEstaActiva(numeroDeCuenta);
                if(cuentaEstaActiva) {
                    boolean pinCorrespondeACuenta = ValidacionCuenta.validarPinCorrespondeACuenta(numeroDeCuenta, pin);
                    if(pinCorrespondeACuenta) {
                        response.sendRedirect("SeleccionDeConsultaEstadoDeCuenta?numeroCuenta=" + numeroDeCuenta);
                    } else {
                    if(this.cantidadDeIntentos == 1) {
                        MensajeEnPantallaCuenta.imprimirMensajeDeErrorPinNoCorrespondeAAcuenta(numeroDeCuenta, pin);
                    request.getRequestDispatcher("ConsultaEstadoDeCuenta.jsp").forward(request, response);
                    }
                    else {
                        validacion.ValidacionCuenta.inactivarCuenta(numeroDeCuenta);
                        request.getRequestDispatcher("ConsultaEstadoDeCuenta.jsp").forward(request, response);
                    }
                  }
                    
                }else{
                    MensajeEnPantallaCuenta.imprimirMensajeDeErrorCuentaInactiva();
                    request.getRequestDispatcher("ConsultaEstadoDeCuenta.jsp").forward(request, response);
                }
            }else{
                MensajeEnPantallaCuenta.imprimirMensajeDeErrorCuentaNoExiste(numeroDeCuenta);
                request.getRequestDispatcher("ConsultaEstadoDeCuenta.jsp").forward(request, response);
            }
    }
    
}

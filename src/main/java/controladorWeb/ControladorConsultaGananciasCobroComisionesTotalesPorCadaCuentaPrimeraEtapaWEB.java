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
@WebServlet(name = "ControladorConsultaGananciasCobroComisionesTotalesPorCadaCuentaPrimeraEtapaWEB", urlPatterns = {"/vistaWeb/ConsultaGananciasCobroComisionesTotalesPorCadaCuentaPrimeraEtapa"})
public class ControladorConsultaGananciasCobroComisionesTotalesPorCadaCuentaPrimeraEtapaWEB extends HttpServlet {
    private int cantidadDeIntentos;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("ConsultaGananciasCobroComisionesTotalesPorCadaCuentaPrimeraEtapa.jsp").forward(request, response);
        this.cantidadDeIntentos = 0;
        }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String numeroDeCuenta = request.getParameter("numeroCuenta");
        String pin = request.getParameter("pin");
        this.cantidadDeIntentos++;
        boolean existeCuenta = ValidacionCuenta.validarExisteCuenta(numeroDeCuenta);
        if(existeCuenta) {
            boolean cuentaEstaActiva = ValidacionCuenta.validarCuentaEstaActiva(numeroDeCuenta);
            if(cuentaEstaActiva) {
                response.sendRedirect("ConsultaGananciasCobroComisionesTotalesPorCadaCuenta?numeroCuenta=" + numeroDeCuenta);
           }else{
                MensajeEnPantallaCuenta.imprimirMensajeDeErrorCuentaInactiva();
                response.sendRedirect("../index.html");
            }
        } else {
            MensajeEnPantallaCuenta.imprimirMensajeDeErrorCuentaNoExiste(numeroDeCuenta);
            response.sendRedirect("../index.html");
          }
}
}

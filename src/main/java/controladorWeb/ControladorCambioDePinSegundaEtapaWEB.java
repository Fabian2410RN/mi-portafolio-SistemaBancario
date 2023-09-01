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
import logicaDeAccesoADatos.DAOCuentaIndividual;
import logicaDeAccesoADatos.IDAOCuentaIndividual;

/**
 *
 * @author sebashdez
 */
@WebServlet(name = "ControladorCambioDePinSegundaEtapaWEB", urlPatterns = {"/vistaWeb/CambioDePinSegundaEtapa"})
public class ControladorCambioDePinSegundaEtapaWEB extends HttpServlet {
    private int cantidadDeIntentos;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String numeroDeCuenta = request.getParameter("numeroCuenta");
        
        request.setAttribute("numeroDeCuenta", numeroDeCuenta);
        this.cantidadDeIntentos = 0;
        
        request.getRequestDispatcher("CambioDePinSegundaEtapa.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        IDAOCuentaIndividual daoCuenta = new DAOCuentaIndividual();
        String pin = request.getParameter("pinActual");
        this.cantidadDeIntentos++;
        String numeroDeCuenta = request.getParameter("numeroDeCuenta");
        boolean pinCorrespondeACuenta = daoCuenta.verificarPinCorrespondeACuenta(numeroDeCuenta, pin);
        if(pinCorrespondeACuenta) {
            response.sendRedirect("CambioDePinTerceraEtapa?numeroCuenta=" + numeroDeCuenta);
        } else {
            if(this.cantidadDeIntentos == 1) {
                MensajeEnPantallaCuenta.imprimirMensajeDeErrorPinNoCorrespondeAAcuenta(numeroDeCuenta, pin);
                request.setAttribute("error", "El pin no corresponde al de la cuenta");
                
                request.setAttribute("numeroDeCuenta", numeroDeCuenta);
                request.getRequestDispatcher("CambioDePinSegundaEtapa.jsp?numeroCuenta=" + numeroDeCuenta).forward(request, response);
            }
            else {
                validacion.ValidacionCuenta.inactivarCuenta(numeroDeCuenta);
                response.sendRedirect("../index.html");
            }

        }
    }
}

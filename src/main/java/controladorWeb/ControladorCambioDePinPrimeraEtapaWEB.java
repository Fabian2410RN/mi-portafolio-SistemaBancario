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
import logicaDeAccesoADatos.DAOCatalogoDeCuentas;
import logicaDeAccesoADatos.IDAOCatalogoDeCuentas;
import validacion.ValidacionCuenta;

/**
 *
 * @author sebashdez
 */
@WebServlet(name = "ControladorCambioDePinPrimeraEtapaWEB", urlPatterns = {"/vistaWeb/CambioDePinPrimeraEtapa"})
public class ControladorCambioDePinPrimeraEtapaWEB extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("CambioDePinPrimeraEtapa.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        IDAOCatalogoDeCuentas daoCuenta = new DAOCatalogoDeCuentas();
        String numeroDeCuenta = request.getParameter("numeroCuenta");
        boolean consultarExisteCuenta = daoCuenta.consultarSiExisteCuenta(numeroDeCuenta);
        if(consultarExisteCuenta) {
            boolean cuentaEstaActiva = ValidacionCuenta.validarCuentaEstaActiva(numeroDeCuenta);
            if(cuentaEstaActiva) {
                response.sendRedirect("CambioDePinSegundaEtapa?numeroCuenta=" + numeroDeCuenta);
            
            }else{
                MensajeEnPantallaCuenta.imprimirMensajeDeErrorCuentaInactiva();
                request.setAttribute("error", "La cuenta esta inactiva");
                request.getRequestDispatcher("CambioDePinPrimeraEtapa.jsp").forward(request, response);
            }
        }
        else {
            MensajeEnPantallaCuenta.imprimirMensajeDeErrorCuentaNoExiste(numeroDeCuenta);
            request.setAttribute("error", "La cuenta no existe");
            request.getRequestDispatcher("CambioDePinPrimeraEtapa.jsp").forward(request, response);
        }
    }
}
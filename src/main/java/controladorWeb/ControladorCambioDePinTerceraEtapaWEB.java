/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladorWeb;

import static controlador.ControladorCambioDePinTerceraEtapa.registrarCambioDePin;
import controlador.MensajeEnPantallaCuenta;
import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logicaDeNegocios.RegistroGeneralBitacoras;
import singletonLogicaDeNegocios.ObjetosTipoBitacoraSingleton;

/**
 *
 * @author sebashdez
 */
@WebServlet(name = "ControladorCambioDePinTerceraEtapaWEB", urlPatterns = {"/vistaWeb/CambioDePinTerceraEtapa"})
public class ControladorCambioDePinTerceraEtapaWEB extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String numeroDeCuenta = request.getParameter("numeroCuenta");
        
        request.setAttribute("numeroDeCuenta", numeroDeCuenta);

        request.getRequestDispatcher("CambioDePinTerceraEtapa.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        String nuevoPin = request.getParameter("pinNuevo");
        String numeroDeCuenta = request.getParameter("numeroDeCuenta");
        
        boolean cambioPinExitoso = registrarCambioDePin(nuevoPin, numeroDeCuenta);
        if (cambioPinExitoso != true){
            
            request.setAttribute("error", "El pin no cumple con el formato");
            request.setAttribute("numeroDeCuenta", numeroDeCuenta);
            request.getRequestDispatcher("CambioDePinTerceraEtapa.jsp?numeroCuenta=" + numeroDeCuenta).forward(request, response);
        }else{
            MensajeEnPantallaCuenta.imprimirMensajeCambioDePinExitoso(numeroDeCuenta);
            RegistroGeneralBitacoras accion = ObjetosTipoBitacoraSingleton.instanciar();
            accion.registrarEnBitacoras(LocalDate.now(), "Cambio de pin", "Web");
            response.sendRedirect("../index.html");
        }
    }
}

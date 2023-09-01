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
import javax.swing.JOptionPane;
import logicaDeAccesoADatos.DAOCuentaIndividual;
import logicaDeAccesoADatos.IDAOCuentaIndividual;
import serviciosExternos.TipoCambioBCCR;
import validacion.ValidacionCuenta;
import static seguridad.seguridadAdministrador.iniciarSesionAdministrador;

/**
 *
 * @author sebashdez
 */
@WebServlet(name = "ControladorValidacionDeAdministradorWEB", urlPatterns = {"/vistaWeb/ValidacionDeAdministrador"})
public class ControladorValidacionDeAdministradorWEB extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("ValidacionDeAdministrador.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String usuario = request.getParameter("usuario");
        String pin = request.getParameter("pin");
        boolean validarUsuarioPin = iniciarSesionAdministrador(usuario,pin);
        if(validarUsuarioPin){
            response.sendRedirect("SeleccionConsultaDeBitacora");
        }else{
            JOptionPane.showMessageDialog(null, "Los credenciales no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
            request.getRequestDispatcher("ValidacionDeAdministrador.jsp").forward(request, response);
        }
        
    }
   
}

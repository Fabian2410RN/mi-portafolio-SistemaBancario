/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladorWeb;

import controlador.ControladorRegistroClientes;
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
@WebServlet(name = "ControladorRegistroClientesWEB", urlPatterns = {"/vistaWeb/RegistroClientes"})
public class ControladorRegistroClientesWEB extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("RegistroClientes.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String primerApellido = request.getParameter("primerApellido");
        String segundoApellido = request.getParameter("segundoApellido");
        String identificacion = request.getParameter("identificacion");
        String fechaDeNacimiento = request.getParameter("fechaNacimiento");
        String numeroDeTelefono = request.getParameter("numeroTelefono");
        String correoElectronico = request.getParameter("correoElectronico");
        
        String diaDeFechaDeNacimiento = fechaDeNacimiento.substring(8, 10);
        String mesDeFechaDeNacimiento = fechaDeNacimiento.substring(5, 7);
        String anoDeFechaDeNacimiento = fechaDeNacimiento.substring(0, 4);
        response.setContentType("text/html; charset=UTF-8");
        
        if (ControladorRegistroClientes.registrarCliente(nombre, primerApellido, segundoApellido, identificacion, diaDeFechaDeNacimiento, mesDeFechaDeNacimiento, anoDeFechaDeNacimiento, numeroDeTelefono, correoElectronico) == true){
            RegistroGeneralBitacoras accion = ObjetosTipoBitacoraSingleton.instanciar();
            accion.registrarEnBitacoras(LocalDate.now(), "Registrar cliente", "Web");
            response.sendRedirect("../index.html");
        } else {
            request.getRequestDispatcher("RegistroClientes.jsp").forward(request, response);
        }
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladorWeb;

import static controlador.ControladorConsultaDeEstatusCuenta.consultarEstatusCuenta;
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
@WebServlet(name = "ControladorConsultaDeEstatusCuentaWEB", urlPatterns = {"/vistaWeb/ConsultaDeEstatusCuenta"})
public class ControladorConsultaDeEstatusCuentaWEB extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("ConsultaDeEstatusCuenta.jsp").forward(request, response);
        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String numeroDeCuenta = request.getParameter("numeroCuenta");
        
        if (consultarEstatusCuenta(numeroDeCuenta) == true){
            RegistroGeneralBitacoras accion = ObjetosTipoBitacoraSingleton.instanciar();
            accion.registrarEnBitacoras(LocalDate.now(), "Consultar estatus de cuenta", "Web");
            response.sendRedirect("../index.html");
        }else{
            request.setAttribute("error", "La cuenta no existe");
            request.getRequestDispatcher("ConsultaDeEstatusCuenta.jsp").forward(request, response);
        }
    }
}

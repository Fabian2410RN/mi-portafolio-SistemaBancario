/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladorWeb;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sebashdez
 */
@WebServlet(name = "ControladorSeleccionDeRetiroEnDolaresOColonesWEB", urlPatterns = {"/vistaWeb/SeleccionDeRetiroEnDolaresOColones"})
public class ControladorSeleccionDeRetiroEnDolaresOColonesWEB extends HttpServlet {
    private String numeroCuenta;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        numeroCuenta = request.getParameter("numeroCuenta");
        request.setAttribute("numeroDeCuenta", numeroCuenta);
        
        request.getRequestDispatcher("SeleccionDeRetiroEnDolaresOColones.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String event = request.getParameter("event");
        if(event.equals("Colones")) {
            response.sendRedirect("SolicitarMontoDeRetiroEnColonesTerceraEtapa?numeroCuenta=" + numeroCuenta);
        }
        if(event.equals("Dolares")) {
            response.sendRedirect("SolicitarMontoDeRetiroEnDolaresTerceraEtapa?numeroCuenta=" + numeroCuenta);
        }
    }
}

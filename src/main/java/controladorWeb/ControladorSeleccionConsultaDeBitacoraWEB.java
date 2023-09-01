/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladorWeb;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author estadm
 */
@WebServlet(name = "ControladorSeleccionConsultaDeBitacoraWEB", urlPatterns = {"/vistaWeb/SeleccionConsultaDeBitacora"})
public class ControladorSeleccionConsultaDeBitacoraWEB extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("SeleccionConsultaDeBitacora.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String event = request.getParameter("event");
        String formato = request.getParameter("formatos");
        
        if(event.equals("Consulta por acciones de hoy")) {
            if(formato.equals("XML")){
                response.sendRedirect("BitacoraXMLConsultaDeAcciones?formato=HOY");
            }
            if(formato.equals("CSV")){
                response.sendRedirect("BitacoraCSVConsultaDeAcciones?formato=HOY");
            }
            if(formato.equals("TXT")){
                response.sendRedirect("BitacoraTXTConsultaDeAcciones?formato=HOY");
            }
        }
           
        if(event.equals("Consulta por acciones de la vista CLI")) {
             if(formato.equals("XML")){
                response.sendRedirect("BitacoraXMLConsultaDeAcciones?formato=CLI");
            }
            if(formato.equals("CSV")){
                response.sendRedirect("BitacoraCSVConsultaDeAcciones?formato=CLI");
            }
            if(formato.equals("TXT")){
                response.sendRedirect("BitacoraTXTConsultaDeAcciones?formato=CLI");
            }
           
        }
        if(event.equals("Consulta por acciones de la vista GUI")) {
            if(formato.equals("XML")){
                response.sendRedirect("BitacoraXMLConsultaDeAcciones?formato=GUI");
            }
            if(formato.equals("CSV")){
                response.sendRedirect("BitacoraCSVConsultaDeAcciones?formato=GUI");
            }
            if(formato.equals("TXT")){
                response.sendRedirect("BitacoraTXTConsultaDeAcciones?formato=GUI");
            }
            
        }
        if(event.equals("Consulta por acciones de la vista WEB")) {
            if(formato.equals("XML")){
                response.sendRedirect("BitacoraXMLConsultaDeAcciones?formato=WEB");
            }
            if(formato.equals("CSV")){
                response.sendRedirect("BitacoraCSVConsultaDeAcciones?formato=WEB");
            }
            if(formato.equals("TXT")){
                response.sendRedirect("BitacoraTXTConsultaDeAcciones?formato=WEB");
            }
            
        }
        if(event.equals("Consulta de todos las acciones")) {
            if(formato.equals("XML")){
                response.sendRedirect("BitacoraXMLConsultaDeAcciones?formato=Todas");
            }
            if(formato.equals("CSV")){
                response.sendRedirect("BitacoraCSVConsultaDeAcciones?formato=Todas");
            }
            if(formato.equals("TXT")){
                response.sendRedirect("BitacoraTXTConsultaDeAcciones?formato=Todas");
            }
            
        }
        
    }
}

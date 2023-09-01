package controladorWeb;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logicaDeNegocios.Bitacora;
import logicaDeNegocios.BitacoraXML;
import logicaDeNegocios.RegistroDeBitacora;
/**
 *
 * @author estadm
 */
@WebServlet(name = "ControladorBitacoraXMLConsultaDeAccionesWEB", urlPatterns = {"/vistaWeb/BitacoraXMLConsultaDeAcciones"})
public class ControladorBitacoraXMLConsultaDeAccionesWEB extends HttpServlet {
    String bitacoraXml;
     @Override
     protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         String formato = request.getParameter("formato");
         request.setAttribute("formato", formato);
         try {
             Bitacora bitacoraXML1 = new BitacoraXML(null);
             if(formato.equals("CLI")){
                 bitacoraXml = bitacoraXML1.consultarRegistrosDeVista("CLI");
             }
             else if(formato.equals("GUI")){
                 bitacoraXml = bitacoraXML1.consultarRegistrosDeVista("GUI");
             }
             else if(formato.equals("WEB")){
                 bitacoraXml = bitacoraXML1.consultarRegistrosDeVista("Web");
             }
             else if(formato.equals("HOY")){
                 bitacoraXml = bitacoraXML1.consultarRegistrosDelDia();
             }
             else{
                 bitacoraXml = bitacoraXML1.consultarTodosLosRegistros();
             }
             
             bitacoraXml = bitacoraXml.replace("<","");
             bitacoraXml = bitacoraXml.replace(">","\n");
             bitacoraXml = bitacoraXml.replace("\n", "<br>");
             request.setAttribute("bitacora", bitacoraXml);
             
         } catch (Exception ex) {
             Logger.getLogger(ControladorBitacoraXMLConsultaDeAccionesWEB.class.getName()).log(Level.SEVERE, null, ex);
         }
         request.getRequestDispatcher("BitacoraXMLConsultaDeAcciones.jsp").forward(request, response);
         
    }
    
     @Override
     protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     }
   
}

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
import logicaDeNegocios.BitacoraCSV;
import logicaDeNegocios.BitacoraXML;
import logicaDeNegocios.RegistroDeBitacora;
/**
 *
 * @author estadm
 */
@WebServlet(name = "ControladorBitacoraCSVConsultaDeAccionesWEB", urlPatterns = {"/vistaWeb/BitacoraCSVConsultaDeAcciones"})
public class ControladorBitacoraCSVConsultaDeAccionesWEB extends HttpServlet {
    String bitacoraCsv;
     @Override
     protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         String formato = request.getParameter("formato");
         request.setAttribute("formato", formato);
         
         try {
             Bitacora bitacoraCSV = new BitacoraCSV(null);
             if(formato.equals("CLI")){
                 bitacoraCsv = bitacoraCSV.consultarRegistrosDeVista("CLI");
             }
             else if(formato.equals("GUI")){
                 bitacoraCsv = bitacoraCSV.consultarRegistrosDeVista("GUI");
             }
             else if(formato.equals("WEB")){
                 bitacoraCsv = bitacoraCSV.consultarRegistrosDeVista("Web");
             }
             else if(formato.equals("HOY")){
                 bitacoraCsv = bitacoraCSV.consultarRegistrosDelDia();
             }
             else{
                  bitacoraCsv = bitacoraCSV.consultarTodosLosRegistros();
             }
             bitacoraCsv = bitacoraCsv.replace("\n", "<br>");
             request.setAttribute("bitacora", bitacoraCsv);
             
         } catch (Exception ex) {
             Logger.getLogger(ControladorBitacoraCSVConsultaDeAccionesWEB.class.getName()).log(Level.SEVERE, null, ex);
         }
         request.getRequestDispatcher("BitacoraCSVConsultaDeAcciones.jsp").forward(request, response);
         
    }
    
     @Override
     protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     }
   
}

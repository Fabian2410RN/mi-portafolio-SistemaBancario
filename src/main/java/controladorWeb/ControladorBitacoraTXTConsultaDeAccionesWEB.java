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
import logicaDeNegocios.BitacoraTramaPlana;
import logicaDeNegocios.BitacoraXML;
import logicaDeNegocios.RegistroDeBitacora;
/**
 *
 * @author estadm
 */
@WebServlet(name = "ControladorBitacoraTXTConsultaDeAccionesWEB", urlPatterns = {"/vistaWeb/BitacoraTXTConsultaDeAcciones"})
public class ControladorBitacoraTXTConsultaDeAccionesWEB extends HttpServlet {
    String bitacoraTxt;
     @Override
     protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         String formato = request.getParameter("formato");
         request.setAttribute("formato", formato);
         
         try {
             Bitacora bitacoraTXT = new BitacoraTramaPlana(null);
             if(formato.equals("CLI")){
                 bitacoraTxt = bitacoraTXT.consultarRegistrosDeVista("CLI");
             }
             else if(formato.equals("GUI")){
                 bitacoraTxt = bitacoraTXT.consultarRegistrosDeVista("GUI");
             }
             else if(formato.equals("WEB")){
                 bitacoraTxt = bitacoraTXT.consultarRegistrosDeVista("Web");
             }
             else if(formato.equals("HOY")){
                 bitacoraTxt = bitacoraTXT.consultarRegistrosDelDia();
             }
             else{
                  bitacoraTxt = bitacoraTXT.consultarTodosLosRegistros();
             }
             System.out.println(bitacoraTxt);
             bitacoraTxt = bitacoraTxt.replace("\n", "<br>");
             
             //bitacoraTxt = bitacoraTxt.replace(" ", "&nbsp");
             System.out.println(bitacoraTxt);
             request.setAttribute("bitacora", bitacoraTxt);
             
         } catch (Exception ex) {
             Logger.getLogger(ControladorBitacoraTXTConsultaDeAccionesWEB.class.getName()).log(Level.SEVERE, null, ex);
         }
         request.getRequestDispatcher("BitacoraTXTConsultaDeAcciones.jsp").forward(request, response);
         
    }
    
     @Override
     protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     }
   
}

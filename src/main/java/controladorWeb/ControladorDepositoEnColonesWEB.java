
package controladorWeb;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controlador.ControladorDepositoEnColones;
import java.time.LocalDate;
import logicaDeNegocios.RegistroGeneralBitacoras;
import singletonLogicaDeNegocios.ObjetosTipoBitacoraSingleton;

/**
 *
 * @author estadm
 */
@WebServlet(name = "ControladorDepositoEnColonesWEB", urlPatterns = {"/vistaWeb/DepositoEnColones"})
public class ControladorDepositoEnColonesWEB extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("DepositoEnColones.jsp").forward(request, response);
    }
    
     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
         String cuenta = request.getParameter("numeroCuenta");
        String montoDeposito = request.getParameter("montoDepositado");
        
        response.setContentType("text/html; charset=UTF-8");
        
        if (ControladorDepositoEnColones.depositarColonesACuenta(cuenta,montoDeposito) == true){
            RegistroGeneralBitacoras accion = ObjetosTipoBitacoraSingleton.instanciar();
            accion.registrarEnBitacoras(LocalDate.now(), "Depósito en colones", "Web");
            response.sendRedirect("../index.html");
        } else {
            request.getRequestDispatcher("DepositoEnColones.jsp").forward(request, response);
        }
    }
    
}


package controladorWeb;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controlador.ControladorRegistroCuentas;
import java.time.LocalDate;
import logicaDeNegocios.RegistroGeneralBitacoras;
import singletonLogicaDeNegocios.ObjetosTipoBitacoraSingleton;

/**
 *
 * @author estadm
 */
@WebServlet(name = "ControladorRegistroCuentasWEB", urlPatterns = {"/vistaWeb/RegistroCuentas"})
public class ControladorRegistroCuentasWEB extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("RegistroCuentas.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String pin = request.getParameter("pin");
        String montoInicial = request.getParameter("montoDepositoInicial");
        String identificacionCliente = request.getParameter("identificacionCliente");
        
        response.setContentType("text/html; charset=UTF-8");
        
        if (ControladorRegistroCuentas.registrarCuenta(pin,montoInicial,identificacionCliente) == true){
            RegistroGeneralBitacoras accion = ObjetosTipoBitacoraSingleton.instanciar();
            accion.registrarEnBitacoras(LocalDate.now(), "Registrar cuenta", "Web");
            response.sendRedirect("../index.html");
        } else {
            request.getRequestDispatcher("RegistroCuentas.jsp").forward(request, response);
        }
    }
}
